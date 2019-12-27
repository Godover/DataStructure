package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图类
 * 
 * @author ziker
 *
 */
public class Graph {
	// 储存所有的顶节点
	private ArrayList<Vertex> vertex;
	// 存放当前所有顶点节点连接信息的阵矩
	private int[][] adjMat;
	// 当前存放顶节点的位置
	private int currentSize;

	public Graph(int size) {
		vertex = new ArrayList<>();
		adjMat = new int[size][size];
	}

	/**
	 * 添加一个顶点
	 * 
	 * @param v 顶点
	 */
	public void addVertex(Vertex v) {
		vertex.add(v);
		// 当前顶点的自己设为通行
		adjMat[currentSize][currentSize] = 0;
		currentSize++;
	}

	/**
	 * 添加一条边
	 * 
	 * @param value1 第一个顶点的值
	 * @param value2 第二个顶点的值
	 */
	public void addEdge(String value1, String value2) {
		int index1 = -1;
		int index2 = -1;
		// 遍历当前所有顶点，找到对用的两个顶点
		for (int i = 0; i < vertex.size(); i++) {
			if (vertex.get(i).getValue().equals(value1)) {
				index1 = i;
			}
			if (vertex.get(i).getValue().equals(value2)) {
				index2 = i;
			}
		}
		// 其中有顶点没有找到
		if (index1 == -1 || index2 == -1) {
			System.out.println("没有找到对应的顶点");
			return;
		}
		// 设置顶点可以连通
		adjMat[index1][index2] = 1;
		adjMat[index2][index1] = 1;
	}

	public int[][] getAdjMat() {
		return adjMat;
	}

	/**
	 * 深度优先搜索算法遍历图
	 */
	public void dfs() {
		System.out.print("深度优先搜索:");
		// 当前搜索的遍历的栈
		Stack<Vertex> stack = new Stack<>();
		// 标边第1个元素已经访问过了
		vertex.get(1).visited = true;
		// 将第1个元素压入栈底
		stack.push(vertex.get(1));
		// 打印第1个元素的值
		System.out.print(vertex.get(1).getValue() + "=>");
		// 这个数组用于记录上一个栈顶的循环次数，就不用每次就从零开始了，优化了时间复杂度
		int[] index = new int[vertex.size()];
		// 当前栈内不为空，及还没有遍历完
		while (!stack.empty()) {
			boolean isContinue = false;
			// 深度搜索找到当前栈顶顶点后，需按照栈顶顶点继续查找.与广度优先搜索不一样的这里没有出栈，
			// 而广度优先搜索这里出队列了
			int currentIndex = vertex.indexOf(stack.peek());
			for (int i = index[currentIndex]; i < vertex.size(); i++) {
				// 当前节点没有访问过，并且当前节点连通
				index[currentIndex] = i;
				if (adjMat[currentIndex][i] == 1 && vertex.get(i).visited == false) {
					// 标记找到了下一个顶点
					isContinue = true;
					// 把下一个元素压入栈中
					stack.push(vertex.get(i));
					// 标记第 i 个节点一访问
					vertex.get(i).visited = true;
					System.out.print(vertex.get(i).getValue() + "=>");
					// 这里找到某个节点为未访问的了，退出for,不继续广度查找，
					break;
				}
			}
			// 找到了下一个顶点，跳过弹出栈顶顶点
			if (isContinue) {
				continue;
			}
			// 弹出栈顶元素
			stack.pop();
		}
		// 更新标记为未访问过
		resetVisited();
	}

	/**
	 * 广度优先搜索算法遍历图，这个只能遍历一个邻接图
	 */
	public void bfs() {
		System.out.print("广度优先搜索:");
		// 当前搜索遍历的队列
		Queue<Vertex> queue = new LinkedList<>();
		// 将第 0 个 入队头
		queue.add(vertex.get(0));
		// 打印第 0 个值
		vertex.get(0).visited = true;
		System.out.print(vertex.get(0).getValue() + "=>");
		// 当前队列不为空
		while (!queue.isEmpty()) {
			// 将队头出队列，并找到index，这里与深度优先搜索不一样的是深度优先搜索没有出栈，这里出队列了
			int currentIndex = vertex.indexOf(queue.poll());
			// 遍历当前顶点的所有邻接顶点
			for (int i = 0; i < vertex.size(); i++) {
				// 当前顶点是连通的并且没有访问过
				if (adjMat[currentIndex][i] == 1 && vertex.get(i).visited == false) {
					// 打印当前顶点的值
					System.out.print(vertex.get(i).getValue() + "=>");
					// 将当前顶点设为已访问过
					vertex.get(i).visited = true;
					// 将顶点入队尾
					queue.add(vertex.get(i));
				}
			}
		}
		// 更新标记为未访问过
		resetVisited();
	}

	/**
	 * 深度优先搜索的递归版本 不能查找图上的所有节点，只能查找与startIndex 连通的图
	 *  如果查找所有的节点，需要进行for遍历vertex的所有未访问过的节点，
	 * 
	 * @param startVertex
	 */
	public void dfsRecursive(int startVertex) {
		System.out.print(vertex.get(startVertex).getValue() + "=>");
		vertex.get(startVertex).visited = true;
		int w = getFirstNeighbor(startVertex);
		while (w != -1) {
			// 当前节点没有被访问过，就递归自己
			if (vertex.get(w).visited == false) {
				dfsRecursive(w);
			}
			w = getNextNeihbor(startVertex, w);
		}
	}

	/**
	 * 得到第一个邻接节点的下标
	 * 
	 * @param index 当前节点的index
	 * @return 如果存在就返回下标，不存在就返回 -1
	 */
	private int getFirstNeighbor(int index) {
		for (int i = 0; i < vertex.size(); i++) {
			if (adjMat[index][i] == 1) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据前一个邻接节点的下标来获取下一个邻接节点
	 * 
	 * @param i 前一个邻接节点的下标
	 * @param w 下一个邻接节点的index
	 * @return 如果存在就返回下标，不存在就返回 -1
	 */
	private int getNextNeihbor(int i, int w) {
		for (int j = w + 1; j < vertex.size(); j++) {
			if (adjMat[i][j] == 1) {
				return j;
			}
		}
		return -1;
	}

	public void resetVisited() {
		for (Vertex v : vertex) {
			v.visited = false;
		}
	}

}
