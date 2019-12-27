package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 迪杰斯特拉算法求两站点间最短路径。。。 
 * 算法的主要思想是:
 * 在实现最短路径时，在每一次去选择权值最小的边进行时会遍历，
 * 但是路径和需要小于当前路径加上目标路径，如果当前路径加目标路径大于了且目标点未被访问，
 * 就替换成最小值，使之能一直保持最小，相当一个修正的过程，理解可以为三角形
 *  A  B  C  三个顶点  AB:3   BC:4   AC:5，从A 出发，第一次会选择到B点，第二次从B点到C点，
 *  在这个去的过程中会检查路径和，AB+BC >  AC ,且 C点未被访问过，则修正成 AC
 * 
 * 
 * 对于权值一样，则需要形成小于等于，
 * 则可以实现由中心点向四周分散扩展 随便那个点触碰到终点以后停止扩展
 * 
 * @author ziker
 *
 */
public class RpDijkstraGraph {
	final static int INF = 0xffffff;
	// 当前的顶点数量
	private int vertexLength;
	// 所有顶点
	private ArrayList<RpDijkstraSite> vertexs;
	// 图的邻接矩阵
	private int[][] matrix;
	// 用于存放起点到各顶点的距离
	private int[] dis;
	private int length;

	public RpDijkstraGraph(int size) {
		this.vertexs = new ArrayList<>();
		this.matrix = new int[size][size];
		this.dis = new int[size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = INF;
			}
		}
	}

	/**
	 * 添加一个顶点
	 * 
	 * @param vertex 顶点
	 */
	public void addVertex(RpDijkstraSite vertex) {
		if (vertexLength > matrix.length) {
			System.out.println("矩阵已经添加满了");
			return;
		}
		int index = findIndexByValue(vertex.getValue());
		if (index != -1) {
			vertexs.get(index).setRoadName(vertex.getRoadName());
			return;
		}
		this.vertexs.add(vertex);
		this.vertexLength = vertexs.size();
	}

	public void addEdge(int v1, int v2) {
		int index1 = findIndexByValue(v1);
		int index2 = findIndexByValue(v2);
		if (index1 == -1) {
			System.out.println("没有找到顶点" + v1);
			return;
		}
		if (index2 == -1) {
			System.out.println("没有找到顶点" + v2);
			return;
		}
		matrix[index1][index2] = 1;
		matrix[index2][index1] = 1;
	}

	/**
	 * 搜索最短路径
	 * 
	 * @param v1 起始点value
	 * @param v2 目标点value
	 */
	public void serachPath(int v1, int v2) {
		resetVisited();
		int start = findIndexByValue(v1);
		RpDijkstraSite startVertex = findVertex(start);
		RpDijkstraSite endVertex = findVertex(findIndexByValue(v2));
		// 初始化起始点状态
		dis[start] = 0;
		startVertex.visited = true;
		// 更新 start 顶点到周围顶点的距离和前驱顶点
		update(start);
		for (int i = 0; i < vertexLength; i++) {
			// 终点被访问够了退出遍历
			if (endVertex.visited) {
				break;
			}
			// 选择下一次的 index
			int index = chooseNextIndex();
			update(index);
		}
		showInfo(endVertex);
		System.out.println(length+"站");
	}

	/**
	 * 选择下一个节点
	 * 
	 * @return
	 */
	private int chooseNextIndex() {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < vertexLength; i++) {
			if (findVertex(i).visited == false && min >= dis[i]) {
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
			// 计算以前的第 j 个邻接节点的总距离 加上本次的距离，这儿就可以间接的理解成 上面距离的三角形校正
			//注意这个地方 第一次取到的 dis[index] 并不是 INF，而是在上面为他初始化的0，第一次不为INF，所以后面所有的节点
			//遍历出来的值也就正确了，因为第一次更新的时候已经就改变过值了，如果不初始化 0 的话，每次都是INF+INF了，就不会进入if了
			len = dis[index] + matrix[index][j]; 
			// 如果本次计算的总距离 小于了 以前的 距离，就替换最新的最短距离
			// 并且当前节点没有被访问过 len < dis[i] 就是类似三角形，1小于3，但是1+4 > 3
			//对于权值一样，就可以理解成让他分散扩展，因为当前距离和总是大于上一次的的距离
			if (len < dis[j] && findVertex(j).visited == false) {
				// 为当前节点设置父节点为 index
				findVertex(j).parent = findVertex(index);
				// 更新距离
				dis[j] = len;
			}
		}
	}

	/**
	 * 更新整个图的状态
	 */
	private void resetVisited() {
		for (RpDijkstraSite v : vertexs) {
			v.visited = false;
			v.parent = null;
		}
		Arrays.fill(dis, INF);
	}

	/**
	 * 打印路径信息
	 * @param vertex 起始打印的节点
	 */
	private void showInfo(RpDijkstraSite vertex) {
		if (vertex.parent != null) {
			showInfo(vertex.parent);
		}
		System.out.print(vertex + " => ");
		length++;
	}
	
	/**
	 * 根据顶点的值找到下标
	 * 
	 * @param value 顶点
	 * @return 顶点对应的下标
	 */
	private int findIndexByValue(int value) {
		for (int i = 0; i < vertexLength; i++) {
			if (vertexs.get(i).getValue() == value) {
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
	private RpDijkstraSite findVertex(int index) {
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
