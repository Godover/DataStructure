package algorithm.dijkstra;

/**
 * �Ͻ�˹�����㷨		
 * ������Ҫ�ص�������ʼ��Ϊ��������� �����չ(�����������˼��)
 * ֱ����չ���յ�Ϊֹ
 * 
 * @author ziker
 *
 */
public class DijkstraAlgorithm {
	public final static int N = 0xffffff;
	
	public static void main(String[] args) {
		String[] data = {"A","B","C","D","E","F","G","H","I","J"};
		int[][] maritx = {
			    /*A B C D E  F G*/
		/*A*/{N,5,7,N,N,N,2},
		/*B*/{5,N,N,9,N,N,3},
		/*C*/{7,N,N,N,8,N,N},
		/*D*/{N,9,N,N,N,4,N},
		/*E*/{N,N,8,N,N,5,4},
		/*F*/{N,N,N,4,5,N,6},
		/*G*/{2,3,N,N,4,6,N}};
		DijkstraGraph graph = new DijkstraGraph(data.length,maritx);
		for (int i = 0; i < data.length; i++) {
			graph.addVertex(new DijkstraVertex(data[i]));
		}
//		graph.showMatrix();
		graph.serachPath("B", "E");
	}
}
