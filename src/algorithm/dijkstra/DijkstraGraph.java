package algorithm.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 迪杰斯特拉算法图类
 * 
 * @author Administrator
 *
 */
public class DijkstraGraph {
	final static int INF = 0xffffff;

	private int vertexLength;
	private ArrayList<DijkstraVertex> vertexs;
	private int[][] matrix;
	private int[] dis;

	public DijkstraGraph(int size, int[][] maritx) {
		this.vertexs = new ArrayList<>();
		this.matrix = maritx;
		this.dis = new int[size];
		Arrays.fill(dis, INF);
	}

	/**
	 * 添加一个顶点
	 * 
	 * @param vertex 顶点
	 */
	public void addVertex(DijkstraVertex vertex) {
		this.vertexs.add(vertex);
		this.vertexLength = vertexs.size();
	}

	/**
	 * 搜索最短路径
	 * 
	 * @param v1 起始点value
	 * @param v2 目标点value
	 */
	public void serachPath(String v1, String v2) {
		resetVisited();
		int start = findIndexByValue(v1);
		DijkstraVertex startVertex = findVertex(start);
		DijkstraVertex endVertex = findVertex(findIndexByValue(v2));
		// 初始化起始点状态
		dis[start] = 0;
		startVertex.visited = true;
		// 更新 start 顶点到周围顶点的距离和前驱顶点
		update(start);
		for (int i = 0; i < vertexLength; i++) {
			if(endVertex.visited) {
				break;
			}
			int index = chooseNextIndex();
			update(index);
		}
		showInfo(endVertex);
	}
	
	private void resetVisited() {
		for (DijkstraVertex v : vertexs) {
			v.visited = false;
			v.parent = null;
		}
		Arrays.fill(dis, INF);
	}
	
	private void showInfo(DijkstraVertex vertex) {
		if(vertex.parent != null) {
			showInfo(vertex.parent);
		}
		if(vertex.parent != null) {
			System.out.println("当前节点:"+vertex.getValue() +"\t父节点:"+vertex.parent.getValue());
		}else {
			System.out.println("起点:"+vertex.getValue());
		}
	}

	/**
	 * 选择下一个节点
	 * @return
	 */
	private int chooseNextIndex() {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < vertexLength; i++) {
			if (findVertex(i).visited == false && min > dis[i]) {
				min = dis[i];
				index = i;
			}
		}
		findVertex(index).visited = true;
		return index;
	}

	/**
	 * 更新index下标到周围顶点的距离和周围顶点的前驱节点
	 * 
	 * @param index index 下标
	 */
	private void update(int index) {
		int len = 0;
		// 遍历邻接矩阵
		for (int j = 0; j < matrix[index].length; j++) {
			// 计算以前的第 j 个邻接节点的总距离 加上本次的距离
			//注意这个地方 第一次取到的 dis[index] 并不是 INF，而是在上面为他初始化的0，第一次不为INF，所以后面所有的节点
			//遍历出来的值也就正确了，因为第一次更新的时候已经就改变过值了，如果不初始化 0 的话，每次都是INF+INF了，就不会进入if了
			len = dis[index] + matrix[index][j];
			// 如果本次计算的总距离 小于了 以前的 距离，就替换最新的最短距离
			// 并且当前节点没有被访问过 len < dis[i] 就是类似三角形，1小于3，但是1+4 > 3
			if (len < dis[j] && findVertex(j).visited == false) {
				// 为当前节点设置父节点为 index
				findVertex(j).parent = findVertex(index);
				// 更新距离
				dis[j] = len;
			}
		}
	}

	/**
	 * 根据顶点的值找到下标
	 * 
	 * @param value 顶点
	 * @return 顶点对应的下标
	 */
	private int findIndexByValue(String value) {
		for (int i = 0; i < vertexLength; i++) {
			if (vertexs.get(i).getValue().equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据找到 id 对应的 vertex
	 * 
	 * @param id 索引位置
	 * @return DijkstraVertex
	 */
	private DijkstraVertex findVertex(int index) {
		return vertexs.get(index);
	}

	/**
	 * 打印邻接矩阵
	 */
	public void showMatrix() {
		for (int i = 0; i < vertexLength; i++) {
			for (int j = 0; j < vertexLength; j++) {
				if (matrix[i][j] == INF) {
					System.out.print("*\t");
				} else {
					System.out.print(matrix[i][j] + "\t");
				}
			}
			System.out.println();
		}
	}
}
