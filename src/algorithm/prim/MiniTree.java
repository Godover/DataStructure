package algorithm.prim;

import java.util.Arrays;

/**
 * ������С������
 * 
 * @author ziker
 *
 */
public class MiniTree {

	/**
	 * ����ͼ
	 * 
	 * @param grapth ͼ����
	 * @param maritx �ڽӾ���
	 * @param data   ͼ����
	 */
	public void createGrapth(MGrapth grapth, int[][] maritx, String[] data) {
		grapth.maritx = maritx;
		grapth.data = data;
	}

	/**
	 * ��ӡͼ����
	 * 
	 * @param grapth ͼ����
	 */
	public void showGrapth(MGrapth grapth) {
		for (int[] link : grapth.maritx) {
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * ����ķ�㷨
	 * 
	 * @param grapth      ͼ����
	 * @param startVertex ��ʼ��
	 */
	public void prim(MGrapth grapth, int startVertex) {
		boolean[] visited = new boolean[grapth.vertexs];
		visited[startVertex] = true;
		// h1 h2 ���ڼ�¼����ѭ������С�ߵ��±�
		int h1 = -1;
		int h2 = -1;
		// ���ڼ�¼�ܺ�
		int sum = 0;
		// ���ɵ� ( n -1 ) ��·�����Դ� 1 ��ʼ
		for (int k = 1; k < grapth.vertexs; k++) {
			// ��ǰ���¾��룬��Ҫ��ÿ��ѭ�������ã�
			int minWeight = PrimAlgorithm.N;
			// �������Ǳ����ʹ��Ľڵ㡣
			for (int i = 0; i < grapth.vertexs; i++) {
				// ��������û�б����ʹ��Ľڵ�
				for (int j = 0; j < grapth.vertexs; j++) {
					// �����ѭ�� visited[i] == true �ͳ����������һ�����裬visited[j] == false �ͳ���������ڶ������裬
					// ˼����Ǳ�����ǰ�Ѿ����ʹ��ĵ�������ڽӵ���û�з��ʹ��Ľڵ㣬�ҵ�Ȩֵ��С��һ���ߣ�
					// ��¼������ע��: �����if ���治�ܸ���visited ��ֵ��������޷�������ǰ��������ڽӵ���
					if (visited[i] == true && visited[j] == false && grapth.maritx[i][j] < minWeight) {
						minWeight = grapth.maritx[i][j];
						// ��¼�±꣬i ���Ƿ��ʹ��ĵ㣬 j �Ƿ��ʵ��ĸ���
						h1 = i;
						h2 = j;
					}
				}
			}
			// �Ѿ��������˵�ǰ�Ѿ����ʹ��ĵ�������ڽӵ㣬������С��Ȩֵ�ĵ�Ϊ�Է���
			visited[h2] = true;
			System.out.println("��һ���ڵ�:" + grapth.data[h1] + "\t�ڶ����ڵ�:" + grapth.data[h2] + "\t·��:" + minWeight);
			sum += minWeight;
		}
		System.out.println("��·��:" + sum);
	}

}
