package algorithm.prim;

/**	
 * ����ķ�㷨		�����·����
 * ����ķ�㷨�ĺ���˼�����ÿ�ζ�ȥ�����з��ʹ��Ľڵ�������ڽӽڵ���û�б����ʹ����ڽӽڵ㣬
 * Ȼ��ȡ��С��Ȩֵ�ı�
 * 
 * @author ziker
 *
 */
public class PrimAlgorithm {
		public final static int N = 0xffffff;
		
		public static void main(String[] args) {
			//����ͼ����
			MGrapth grapth = new MGrapth(7);
			//ͼ����
			String[] data = {"A","B","C","D","E","F","G"};
			//����
			int[][] maritx = {
					    /*A B C D E  F G*/
				/*A*/{N,5,7,N,N,N,2},
				/*B*/{5,N,N,9,N,N,3},
				/*C*/{7,N,N,N,8,N,N},
				/*D*/{N,9,N,N,N,4,N},
				/*E*/{N,N,8,N,N,5,4},
				/*F*/{N,N,N,4,5,N,6},
				/*G*/{2,3,N,N,4,6,N}};
			
			MiniTree miniTree = new MiniTree();
			miniTree.createGrapth(grapth, maritx, data);
			miniTree.prim(grapth, 4);
			
		}
}

