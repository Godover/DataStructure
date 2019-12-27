package algorithm.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * �Ͻ�˹�����㷨ͼ��
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
	 * ���һ������
	 * 
	 * @param vertex ����
	 */
	public void addVertex(DijkstraVertex vertex) {
		this.vertexs.add(vertex);
		this.vertexLength = vertexs.size();
	}

	/**
	 * �������·��
	 * 
	 * @param v1 ��ʼ��value
	 * @param v2 Ŀ���value
	 */
	public void serachPath(String v1, String v2) {
		resetVisited();
		int start = findIndexByValue(v1);
		DijkstraVertex startVertex = findVertex(start);
		DijkstraVertex endVertex = findVertex(findIndexByValue(v2));
		// ��ʼ����ʼ��״̬
		dis[start] = 0;
		startVertex.visited = true;
		// ���� start ���㵽��Χ����ľ����ǰ������
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
			System.out.println("��ǰ�ڵ�:"+vertex.getValue() +"\t���ڵ�:"+vertex.parent.getValue());
		}else {
			System.out.println("���:"+vertex.getValue());
		}
	}

	/**
	 * ѡ����һ���ڵ�
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
	 * ����index�±굽��Χ����ľ������Χ�����ǰ���ڵ�
	 * 
	 * @param index index �±�
	 */
	private void update(int index) {
		int len = 0;
		// �����ڽӾ���
		for (int j = 0; j < matrix[index].length; j++) {
			// ������ǰ�ĵ� j ���ڽӽڵ���ܾ��� ���ϱ��εľ���
			//ע������ط� ��һ��ȡ���� dis[index] ������ INF������������Ϊ����ʼ����0����һ�β�ΪINF�����Ժ������еĽڵ�
			//����������ֵҲ����ȷ�ˣ���Ϊ��һ�θ��µ�ʱ���Ѿ��͸ı��ֵ�ˣ��������ʼ�� 0 �Ļ���ÿ�ζ���INF+INF�ˣ��Ͳ������if��
			len = dis[index] + matrix[index][j];
			// ������μ�����ܾ��� С���� ��ǰ�� ���룬���滻���µ���̾���
			// ���ҵ�ǰ�ڵ�û�б����ʹ� len < dis[i] �������������Σ�1С��3������1+4 > 3
			if (len < dis[j] && findVertex(j).visited == false) {
				// Ϊ��ǰ�ڵ����ø��ڵ�Ϊ index
				findVertex(j).parent = findVertex(index);
				// ���¾���
				dis[j] = len;
			}
		}
	}

	/**
	 * ���ݶ����ֵ�ҵ��±�
	 * 
	 * @param value ����
	 * @return �����Ӧ���±�
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
	 * �����ҵ� id ��Ӧ�� vertex
	 * 
	 * @param id ����λ��
	 * @return DijkstraVertex
	 */
	private DijkstraVertex findVertex(int index) {
		return vertexs.get(index);
	}

	/**
	 * ��ӡ�ڽӾ���
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
