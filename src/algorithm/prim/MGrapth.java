package algorithm.prim;

/**
 * ����ķ�㷨ͼ�ṹ
 * 
 * @author ziker
 *
 */
public class MGrapth {
	// ͼ�Ķ�����
	int vertexs;
	// ͼ���ڽӾ���
	int[][] maritx;
	// ͼ�Ķ�������
	String[] data;

	public MGrapth(int vertexs) {
		this.vertexs = vertexs;
		this.maritx = new int[vertexs][vertexs];
		this.data = new String[vertexs];
	}
}
