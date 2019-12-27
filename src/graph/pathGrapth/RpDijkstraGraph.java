package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * �Ͻ�˹�����㷨����վ������·�������� 
 * �㷨����Ҫ˼����:
 * ��ʵ�����·��ʱ����ÿһ��ȥѡ��Ȩֵ��С�ı߽���ʱ�������
 * ����·������ҪС�ڵ�ǰ·������Ŀ��·���������ǰ·����Ŀ��·����������Ŀ���δ�����ʣ�
 * ���滻����Сֵ��ʹ֮��һֱ������С���൱һ�������Ĺ��̣�������Ϊ������
 *  A  B  C  ��������  AB:3   BC:4   AC:5����A ��������һ�λ�ѡ��B�㣬�ڶ��δ�B�㵽C�㣬
 *  �����ȥ�Ĺ����л���·���ͣ�AB+BC >  AC ,�� C��δ�����ʹ����������� AC
 * 
 * 
 * ����Ȩֵһ��������Ҫ�γ�С�ڵ��ڣ�
 * �����ʵ�������ĵ������ܷ�ɢ��չ ����Ǹ��㴥�����յ��Ժ�ֹͣ��չ
 * 
 * @author ziker
 *
 */
public class RpDijkstraGraph {
	final static int INF = 0xffffff;
	// ��ǰ�Ķ�������
	private int vertexLength;
	// ���ж���
	private ArrayList<RpDijkstraSite> vertexs;
	// ͼ���ڽӾ���
	private int[][] matrix;
	// ���ڴ����㵽������ľ���
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
	 * ���һ������
	 * 
	 * @param vertex ����
	 */
	public void addVertex(RpDijkstraSite vertex) {
		if (vertexLength > matrix.length) {
			System.out.println("�����Ѿ��������");
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
			System.out.println("û���ҵ�����" + v1);
			return;
		}
		if (index2 == -1) {
			System.out.println("û���ҵ�����" + v2);
			return;
		}
		matrix[index1][index2] = 1;
		matrix[index2][index1] = 1;
	}

	/**
	 * �������·��
	 * 
	 * @param v1 ��ʼ��value
	 * @param v2 Ŀ���value
	 */
	public void serachPath(int v1, int v2) {
		resetVisited();
		int start = findIndexByValue(v1);
		RpDijkstraSite startVertex = findVertex(start);
		RpDijkstraSite endVertex = findVertex(findIndexByValue(v2));
		// ��ʼ����ʼ��״̬
		dis[start] = 0;
		startVertex.visited = true;
		// ���� start ���㵽��Χ����ľ����ǰ������
		update(start);
		for (int i = 0; i < vertexLength; i++) {
			// �յ㱻���ʹ����˳�����
			if (endVertex.visited) {
				break;
			}
			// ѡ����һ�ε� index
			int index = chooseNextIndex();
			update(index);
		}
		showInfo(endVertex);
		System.out.println(length+"վ");
	}

	/**
	 * ѡ����һ���ڵ�
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
	 * ����index�±굽��Χ����ľ������Χ�����ǰ���ڵ�
	 * 
	 * @param index index �±�
	 */
	private void update(int index) {
		int len = 0;
		// �����ڽӾ���
		for (int j = 0; j < matrix[index].length; j++) {
			// ������ǰ�ĵ� j ���ڽӽڵ���ܾ��� ���ϱ��εľ��룬����Ϳ��Լ�ӵ����� ��������������У��
			//ע������ط� ��һ��ȡ���� dis[index] ������ INF������������Ϊ����ʼ����0����һ�β�ΪINF�����Ժ������еĽڵ�
			//����������ֵҲ����ȷ�ˣ���Ϊ��һ�θ��µ�ʱ���Ѿ��͸ı��ֵ�ˣ��������ʼ�� 0 �Ļ���ÿ�ζ���INF+INF�ˣ��Ͳ������if��
			len = dis[index] + matrix[index][j]; 
			// ������μ�����ܾ��� С���� ��ǰ�� ���룬���滻���µ���̾���
			// ���ҵ�ǰ�ڵ�û�б����ʹ� len < dis[i] �������������Σ�1С��3������1+4 > 3
			//����Ȩֵһ�����Ϳ�������������ɢ��չ����Ϊ��ǰ��������Ǵ�����һ�εĵľ���
			if (len < dis[j] && findVertex(j).visited == false) {
				// Ϊ��ǰ�ڵ����ø��ڵ�Ϊ index
				findVertex(j).parent = findVertex(index);
				// ���¾���
				dis[j] = len;
			}
		}
	}

	/**
	 * ��������ͼ��״̬
	 */
	private void resetVisited() {
		for (RpDijkstraSite v : vertexs) {
			v.visited = false;
			v.parent = null;
		}
		Arrays.fill(dis, INF);
	}

	/**
	 * ��ӡ·����Ϣ
	 * @param vertex ��ʼ��ӡ�Ľڵ�
	 */
	private void showInfo(RpDijkstraSite vertex) {
		if (vertex.parent != null) {
			showInfo(vertex.parent);
		}
		System.out.print(vertex + " => ");
		length++;
	}
	
	/**
	 * ���ݶ����ֵ�ҵ��±�
	 * 
	 * @param value ����
	 * @return �����Ӧ���±�
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
	 * �����ҵ� id ��Ӧ�� vertex
	 * 
	 * @param id ����λ��
	 * @return DijkstraVertex
	 */
	private RpDijkstraSite findVertex(int index) {
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
