package algorithm.kruskal;

/**
 * ��³˹�����㷨ͼ�ṹ
 * 
 * @author ziker
 *
 */
public class MGrapth {
	int INF = Integer.MAX_VALUE;

	int vertex;
	// �ߵ�����
	int edgeNumber;
	// ͼ���ڽӾ���
	int[][] matrix;
	// ͼ�Ķ�������
	String[] data;

	/**
	 * ��ʼ��
	 * 
	 * @param data
	 * @param maritx
	 */
	public MGrapth(String[] data, int[][] maritx) {
		this.vertex = data.length;
		this.matrix = maritx;
		this.data = data;
		//����ߵ�����
		for (int i = 0; i < vertex; i++) {
			for (int j = i + 1; j < vertex; j++) {
				if (maritx[i][j] != INF) {
					edgeNumber++;
				}
			}
		}
	}

	public void kruskal() {
		int index = 0;
		// �������е���С�������е�ÿ����������С�������е��յ�
		int[] ends = new int[edgeNumber];
		// ������С�������ļ���
		Edge[] rets = new Edge[edgeNumber];
		// ��ȡͼ�����бߵļ���
		Edge[] edges = getEdges();
		// ���ձߵ�Ȩֵ��С����
		sortEdge(edges);
		// ����edges���飬������ӵ���С�������У��ж��Ƿ��γɻ�· ���û�о���ӽ��������
		for (int i = 0; i < edges.length; i++) {
			// �ҵ���ǰ�ߵ������յ�
			int start = getPosition(edges[i].start);
			int end = getPosition(edges[i].end);
			// ������ȡ������ĸ��ڵ��յ�����
			int m = getEnd(ends, start);
			int n = getEnd(ends, end);
			// �����ж��յ������Ƿ�һ�������һ����˵�����ɻ�·����һ����û�й��ɻ�·
			if (m != n) {
				ends[m] = n;
				//���ﲻ��Ҫ ends[n] = n;  genEnd ��while ������� ends[i] !=0,�Ͱ� i = ends[i],����i����ԭ����ֵ��
				// ����,ends[m] = n;�Ͳ�һ���ˣ� 
				rets[index++] = edges[i];
			}
		}
		for (int j = 0; rets[j] != null; j++) {
			System.out.println(rets[j].toString());
		}
	}

	/**
	 * ����±�Ϊ i ���յ㣬�����ж������ߵĸ��ڵ��Ƿ���ͬ
	 * 
	 * @param ends ��¼�յ������
	 * @param i    ����Ķ�����±�
	 * @return ���ص����±�Ϊ i ����������Ӧ���յ�
	 */
	public int getEnd(int[] ends, int i) {
		while (ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}

	/**
	 * �Աߵ�Ȩֵ��������
	 * 
	 * @param edges
	 */
	public void sortEdge(Edge[] edges) {
		//ʹ��ѡ������Ա�Ȩ��������
		for (int i = 0; i < edges.length - 1; i++) {
			for (int j = i + 1; j < edges.length; j++) {
				if (edges[i].weight > edges[j].weight) {
					Edge tEdge = edges[i];
					edges[i] = edges[j];
					edges[j] = tEdge;
				}
			}
		}
	}

	/**
	 * �õ����б�
	 * 
	 * @return
	 */
	public Edge[] getEdges() {
		Edge[] edges = new Edge[edgeNumber];
		int num = 0;
		// ����ͼ�� �ڽӾ����ҵ����ұߣ���������
		for (int i = 0; i < vertex - 1; i++) {
			for (int j = i + 1; j < vertex; j++) {
				if (matrix[i][j] != INF) {
					edges[num++] = new Edge(data[i], data[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}

	/**
	 * �ҵ���Ӧ�Ķ�������
	 * 
	 * @param vertex ��������
	 * @return ���������
	 */
	private int getPosition(String vertex) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(vertex)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * ��ӡͼ
	 */
	public void showMatrix() {
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
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
