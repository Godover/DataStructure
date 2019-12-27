package graph.pathGrapth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * ������·��
 * 
 * @author ziker
 *
 */
public class GraphTest {
	// ������·��Ϣ
	private static ArrayList<PathBean> data = new ArrayList<>();
	// ������·����
	private static ArrayList<String> roadnames = new ArrayList<>();
	// ����վ���Ӧ����ֵ
	private static HashMap<String, Integer> namesToInt = new HashMap<>(150);
	/** ��·վ���Ӧ��ֵӳ�䣬 */
	private static ArrayList<HashMap<String, Integer>> nameItems = new ArrayList<>(8);
	// ����Ӧ��ֵ������·����Ϊhash�����޷���ʾ
	@SuppressWarnings("unchecked")
	private static ArrayList<Integer>[] allIndex = new ArrayList[8];
	
	/** dijKstra �㷨��ͼ�� */
	private static RpDijkstraGraph graphDjs;
	/** ���������ͼ��  */
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
	 * ��������·��Ϣӳ�䵽��������
	 */
	private static void initData() {
		try {
			JSONArray array = new JSONObject(PathInfo.json).getJSONArray("ROWS_DETAIL");
			for (int i = 0; i < array.length(); i++) {
				PathBean info = new Gson().fromJson(array.get(i).toString(), PathBean.class);
				data.add(info);
				String name = info.getName().substring(info.getName().indexOf("��") + 1, info.getName().indexOf("��"));
				roadnames.add(name);
			}
		} catch (Exception e) {}
	}

	/**
	 * ����վ����Ϣ
	 */
	private static void initSite() {
		int position = 0;
		// ����վ���Ӧ����ֵ
		for (int i = 0; i < data.size(); i++) {
			List<String> item = data.get(i).getSites();
			for (int j = 0; j < item.size(); j++) {
				if (namesToInt.get(item.get(j)) == null) {
					namesToInt.put(item.get(j), position++);
				}
			}
		}
		// ����hash���ÿ����·�Ķ�Ӧ����ֵ
		for (int i = 0; i < data.size(); i++) {
			List<String> item = data.get(i).getSites();
			HashMap<String, Integer> map = new HashMap<>(item.size());
			for (int j = 0; j < item.size(); j++) {
				map.put(item.get(j), namesToInt.get(item.get(j)));
			}
			nameItems.add(map);
		}
		// ����Ӧ��ֵ������·����Ϊhash�����޷���ʾ
		for (int i = 0; i < allIndex.length; i++) {
			// ��ʼ��ÿ�� allIndex
			allIndex[i] = new ArrayList<Integer>();
			// �õ���ǰ��·������վ��
			List<String> tempSites = data.get(i).getSites();
			// tempSet ��ʾ��ǰ��ǰ��·վ���Ӧ��ֵӳ��
			HashMap<String, Integer> tempSet = nameItems.get(i);
			for (int j = 0; j < tempSites.size(); j++) {
				allIndex[i].add(tempSet.get(tempSites.get(j)));
			}
		}
	}

	/**
	 * ����ͼ��Ϣ
	 */
	private static void initGrapth() {
		// ��ʼ��ͼ
		graphDjs = new RpDijkstraGraph(namesToInt.size());
		graph = new RpGraph(namesToInt.size());
		
		for (int j=0;j<allIndex.length;j++) {
			ArrayList<Integer> arrayList = allIndex[j];
			for (int i = 0; i < arrayList.size(); i++) {
				// ������ж���
				//dijKstra �㷨��ͼ�������Ϣ
				graphDjs.addVertex(new RpDijkstraSite(arrayList.get(i), roadnames.get(j)));
				// ������бߣ���Ϊ��ӱ���Ҫ�ӵ�һ����ʼ�������ǵ�0����
				if (i != 0) {
					graphDjs.addEdge(arrayList.get(i - 1), arrayList.get(i));
				}
				// ������ж���
				//������� �㷨��ͼ�������Ϣ
				graph.addVertex(new RpSite(arrayList.get(i), roadnames.get(j)));
				// ������бߣ���Ϊ��ӱ���Ҫ�ӵ�һ����ʼ�������ǵ�0����
				if (i != 0) {
					graph.addEdge(arrayList.get(i - 1), arrayList.get(i));
				}
			}
		}	
	}
	
	/**
	 * �������·��  ������ֵ
	 * @param v1 ��ʼ��value
	 * @param v2 �յ�value
	 */
	private static void searchDijkstraRp(int v1,int v2) {
		graphDjs.serachPath(v1, v2);
	}
	
	/**
	 * �������·��  ������ֵ
	 * @param v1 ��ʼ��value
	 * @param v2 �յ�value
	 */
	private static void searchRp(int v1,int v2) {
		Stack<RpSite> stack = graph.dfs(v1,v2);
		int length =  stack.size();
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().toString()+" => ");
		}
		System.out.println(length+"վ");
	}
}
