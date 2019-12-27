package graph.pathGrapth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 地铁线路类
 * 
 * @author ziker
 *
 */
public class GraphTest {
	// 所有线路信息
	private static ArrayList<PathBean> data = new ArrayList<>();
	// 所有线路名称
	private static ArrayList<String> roadnames = new ArrayList<>();
	// 构造站点对应的数值
	private static HashMap<String, Integer> namesToInt = new HashMap<>(150);
	/** 线路站点对应的值映射， */
	private static ArrayList<HashMap<String, Integer>> nameItems = new ArrayList<>(8);
	// 将对应的值存入线路，因为hash无序，无法表示
	@SuppressWarnings("unchecked")
	private static ArrayList<Integer>[] allIndex = new ArrayList[8];
	
	/** dijKstra 算法的图类 */
	private static RpDijkstraGraph graphDjs;
	/** 深度搜索的图类  */
	private static RpGraph graph;
	
	
	public static void main(String[] args) {
		initData();
		initSite();
		initGrapth();
		
		searchRp(127,40);
		System.out.println();
		searchDijkstraRp(127,40);
	}

	/**
	 * 将所有线路信息映射到数据里面
	 */
	private static void initData() {
		try {
			JSONArray array = new JSONObject(PathInfo.json).getJSONArray("ROWS_DETAIL");
			for (int i = 0; i < array.length(); i++) {
				PathBean info = new Gson().fromJson(array.get(i).toString(), PathBean.class);
				data.add(info);
				String name = info.getName().substring(info.getName().indexOf("铁") + 1, info.getName().indexOf("号"));
				roadnames.add(name);
			}
		} catch (Exception e) {}
	}

	/**
	 * 构造站点信息
	 */
	private static void initSite() {
		int position = 0;
		// 构造站点对应的数值
		for (int i = 0; i < data.size(); i++) {
			List<String> item = data.get(i).getSites();
			for (int j = 0; j < item.size(); j++) {
				if (namesToInt.get(item.get(j)) == null) {
					namesToInt.put(item.get(j), position++);
				}
			}
		}
		// 构造hash表存每条线路的对应的数值
		for (int i = 0; i < data.size(); i++) {
			List<String> item = data.get(i).getSites();
			HashMap<String, Integer> map = new HashMap<>(item.size());
			for (int j = 0; j < item.size(); j++) {
				map.put(item.get(j), namesToInt.get(item.get(j)));
			}
			nameItems.add(map);
		}
		// 将对应的值存入线路，因为hash无序，无法表示
		for (int i = 0; i < allIndex.length; i++) {
			// 初始化每个 allIndex
			allIndex[i] = new ArrayList<Integer>();
			// 拿到当前线路的所有站点
			List<String> tempSites = data.get(i).getSites();
			// tempSet 表示当前当前线路站点对应的值映射
			HashMap<String, Integer> tempSet = nameItems.get(i);
			for (int j = 0; j < tempSites.size(); j++) {
				allIndex[i].add(tempSet.get(tempSites.get(j)));
			}
		}
	}

	/**
	 * 构造图信息
	 */
	private static void initGrapth() {
		// 初始化图
		graphDjs = new RpDijkstraGraph(namesToInt.size());
		graph = new RpGraph(namesToInt.size());
		
		for (int j=0;j<allIndex.length;j++) {
			ArrayList<Integer> arrayList = allIndex[j];
			for (int i = 0; i < arrayList.size(); i++) {
				// 添加所有顶点
				//dijKstra 算法的图类添加信息
				graphDjs.addVertex(new RpDijkstraSite(arrayList.get(i), roadnames.get(j)));
				// 添加所有边，因为添加边需要从第一个开始，而不是第0个，
				if (i != 0) {
					graphDjs.addEdge(arrayList.get(i - 1), arrayList.get(i));
				}
				// 添加所有顶点
				//深度搜索 算法的图类添加信息
				graph.addVertex(new RpSite(arrayList.get(i), roadnames.get(j)));
				// 添加所有边，因为添加边需要从第一个开始，而不是第0个，
				if (i != 0) {
					graph.addEdge(arrayList.get(i - 1), arrayList.get(i));
				}
			}
		}	
	}
	
	/**
	 * 深度搜索路径  不是最值
	 * @param v1 起始点value
	 * @param v2 终点value
	 */
	private static void searchDijkstraRp(int v1,int v2) {
		graphDjs.serachPath(v1, v2);
	}
	
	/**
	 * 深度搜索路径  不是最值
	 * @param v1 起始点value
	 * @param v2 终点value
	 */
	private static void searchRp(int v1,int v2) {
		Stack<RpSite> stack = graph.dfs(v1,v2);
		int length =  stack.size();
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().toString()+" => ");
		}
		System.out.println(length+"站");
	}
}
