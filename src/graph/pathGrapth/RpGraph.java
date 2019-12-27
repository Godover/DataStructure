package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 深度，广度 搜索 地铁信息图类
 * 
 * @author ziker
 *
 */
public class RpGraph {
	// 当前所有顶点
	private ArrayList<RpSite> roadSites;
	// 表示图的矩阵
	private int[][] matrix;

	public RpGraph(int size) {
		roadSites = new ArrayList<>();
		matrix = new int[size][size];
	}

	public void addVertex(RpSite site) {
		if (roadSites.size() > matrix.length) {
			System.out.println("矩阵已经添加满了");
			return;
		}
		int index = findSiteIndex(site.getValue());
		if(index != -1) {
			roadSites.get(index).setRoadName(site.getRoadName());
			return;
		}
		matrix[roadSites.size()][roadSites.size()] = 1;
		roadSites.add(site);
	}

	public void addEdge(int v1, int v2) {
		int index1 = findSiteIndex(v1);
		int index2 = findSiteIndex(v2);
		if (index1 == -1) {
			System.out.println("没有找到顶点"+v1);
			return;
		}
		if (index2 == -1) {
			System.out.println("没有找到顶点"+v2);
			return;
		}
		matrix[index1][index2] = 1;
		matrix[index2][index1] = 1;
	}
	
	public int[][] getMatrix() {
		return matrix;
	}
	
	public RpSite getSite(int value) {
		int index = findSiteIndex(value);
		if(index != -1) {
			return roadSites.get(index);
		}
		return null;
	}

	public Stack<RpSite> dfs(int v1, int v2) {
		RpSite startSite = roadSites.get(findSiteIndex(v1));
		RpSite endSite = roadSites.get(findSiteIndex(v2));
		System.out.println(startSite.getValue()+"\t"+endSite.getValue());
		Stack<RpSite> stack = new Stack<>();
		stack.add(startSite);
		startSite.isVisited = true;
		while (!stack.isEmpty()) {
			boolean isContinue =false;
			boolean isBreak = false;
			int curentIndex = roadSites.indexOf(stack.peek());
			for (int i = 0; i < roadSites.size(); i++) {
				RpSite tempSite = roadSites.get(i);
				if(tempSite.isVisited == false && matrix[curentIndex][i] == 1) {
					tempSite.isVisited = true;
					stack.push(tempSite);
					if(tempSite == endSite) {
						isBreak = true;
					}
					isContinue = true;
					break;
				}
			}
			
			if(isBreak) {
				break;
			}
			if(isContinue) {
				continue;
			}
			stack.pop();
		}
		Stack<RpSite> queue = new Stack<>();
		while(!stack.isEmpty()) {
			queue.push(stack.pop());
		}
		return queue;
	}
	
	private int  findSiteIndex(int v) {
		for (RpSite site : roadSites) {
			if(site.getValue() == v) {
				return roadSites.indexOf(site);
			}
		}
		return -1;
	}
	
}






