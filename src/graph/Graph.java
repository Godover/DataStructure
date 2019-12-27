package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ͼ��
 * 
 * @author ziker
 *
 */
public class Graph {
	// �������еĶ��ڵ�
	private ArrayList<Vertex> vertex;
	// ��ŵ�ǰ���ж���ڵ�������Ϣ�����
	private int[][] adjMat;
	// ��ǰ��Ŷ��ڵ��λ��
	private int currentSize;

	public Graph(int size) {
		vertex = new ArrayList<>();
		adjMat = new int[size][size];
	}

	/**
	 * ���һ������
	 * 
	 * @param v ����
	 */
	public void addVertex(Vertex v) {
		vertex.add(v);
		// ��ǰ������Լ���Ϊͨ��
		adjMat[currentSize][currentSize] = 0;
		currentSize++;
	}

	/**
	 * ���һ����
	 * 
	 * @param value1 ��һ�������ֵ
	 * @param value2 �ڶ��������ֵ
	 */
	public void addEdge(String value1, String value2) {
		int index1 = -1;
		int index2 = -1;
		// ������ǰ���ж��㣬�ҵ����õ���������
		for (int i = 0; i < vertex.size(); i++) {
			if (vertex.get(i).getValue().equals(value1)) {
				index1 = i;
			}
			if (vertex.get(i).getValue().equals(value2)) {
				index2 = i;
			}
		}
		// �����ж���û���ҵ�
		if (index1 == -1 || index2 == -1) {
			System.out.println("û���ҵ���Ӧ�Ķ���");
			return;
		}
		// ���ö��������ͨ
		adjMat[index1][index2] = 1;
		adjMat[index2][index1] = 1;
	}

	public int[][] getAdjMat() {
		return adjMat;
	}

	/**
	 * ������������㷨����ͼ
	 */
	public void dfs() {
		System.out.print("�����������:");
		// ��ǰ�����ı�����ջ
		Stack<Vertex> stack = new Stack<>();
		// ��ߵ�1��Ԫ���Ѿ����ʹ���
		vertex.get(1).visited = true;
		// ����1��Ԫ��ѹ��ջ��
		stack.push(vertex.get(1));
		// ��ӡ��1��Ԫ�ص�ֵ
		System.out.print(vertex.get(1).getValue() + "=>");
		// ����������ڼ�¼��һ��ջ����ѭ���������Ͳ���ÿ�ξʹ��㿪ʼ�ˣ��Ż���ʱ�临�Ӷ�
		int[] index = new int[vertex.size()];
		// ��ǰջ�ڲ�Ϊ�գ�����û�б�����
		while (!stack.empty()) {
			boolean isContinue = false;
			// ��������ҵ���ǰջ��������谴��ջ�������������.��������������һ��������û�г�ջ��
			// ������������������������
			int currentIndex = vertex.indexOf(stack.peek());
			for (int i = index[currentIndex]; i < vertex.size(); i++) {
				// ��ǰ�ڵ�û�з��ʹ������ҵ�ǰ�ڵ���ͨ
				index[currentIndex] = i;
				if (adjMat[currentIndex][i] == 1 && vertex.get(i).visited == false) {
					// ����ҵ�����һ������
					isContinue = true;
					// ����һ��Ԫ��ѹ��ջ��
					stack.push(vertex.get(i));
					// ��ǵ� i ���ڵ�һ����
					vertex.get(i).visited = true;
					System.out.print(vertex.get(i).getValue() + "=>");
					// �����ҵ�ĳ���ڵ�Ϊδ���ʵ��ˣ��˳�for,��������Ȳ��ң�
					break;
				}
			}
			// �ҵ�����һ�����㣬��������ջ������
			if (isContinue) {
				continue;
			}
			// ����ջ��Ԫ��
			stack.pop();
		}
		// ���±��Ϊδ���ʹ�
		resetVisited();
	}

	/**
	 * ������������㷨����ͼ�����ֻ�ܱ���һ���ڽ�ͼ
	 */
	public void bfs() {
		System.out.print("�����������:");
		// ��ǰ���������Ķ���
		Queue<Vertex> queue = new LinkedList<>();
		// ���� 0 �� ���ͷ
		queue.add(vertex.get(0));
		// ��ӡ�� 0 ��ֵ
		vertex.get(0).visited = true;
		System.out.print(vertex.get(0).getValue() + "=>");
		// ��ǰ���в�Ϊ��
		while (!queue.isEmpty()) {
			// ����ͷ�����У����ҵ�index���������������������һ�����������������û�г�ջ�������������
			int currentIndex = vertex.indexOf(queue.poll());
			// ������ǰ����������ڽӶ���
			for (int i = 0; i < vertex.size(); i++) {
				// ��ǰ��������ͨ�Ĳ���û�з��ʹ�
				if (adjMat[currentIndex][i] == 1 && vertex.get(i).visited == false) {
					// ��ӡ��ǰ�����ֵ
					System.out.print(vertex.get(i).getValue() + "=>");
					// ����ǰ������Ϊ�ѷ��ʹ�
					vertex.get(i).visited = true;
					// ���������β
					queue.add(vertex.get(i));
				}
			}
		}
		// ���±��Ϊδ���ʹ�
		resetVisited();
	}

	/**
	 * ������������ĵݹ�汾 ���ܲ���ͼ�ϵ����нڵ㣬ֻ�ܲ�����startIndex ��ͨ��ͼ
	 *  ����������еĽڵ㣬��Ҫ����for����vertex������δ���ʹ��Ľڵ㣬
	 * 
	 * @param startVertex
	 */
	public void dfsRecursive(int startVertex) {
		System.out.print(vertex.get(startVertex).getValue() + "=>");
		vertex.get(startVertex).visited = true;
		int w = getFirstNeighbor(startVertex);
		while (w != -1) {
			// ��ǰ�ڵ�û�б����ʹ����͵ݹ��Լ�
			if (vertex.get(w).visited == false) {
				dfsRecursive(w);
			}
			w = getNextNeihbor(startVertex, w);
		}
	}

	/**
	 * �õ���һ���ڽӽڵ���±�
	 * 
	 * @param index ��ǰ�ڵ��index
	 * @return ������ھͷ����±꣬�����ھͷ��� -1
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
	 * ����ǰһ���ڽӽڵ���±�����ȡ��һ���ڽӽڵ�
	 * 
	 * @param i ǰһ���ڽӽڵ���±�
	 * @param w ��һ���ڽӽڵ��index
	 * @return ������ھͷ����±꣬�����ھͷ��� -1
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
