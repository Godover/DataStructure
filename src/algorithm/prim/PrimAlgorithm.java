package algorithm.prim;

/**	
 * 普利姆算法		解决修路问题
 * 普利姆算法的核心思想就是每次都去找所有访问过的节点的所有邻接节点中没有被访问过的邻接节点，
 * 然后取最小的权值的边
 * 
 * @author ziker
 *
 */
public class PrimAlgorithm {
		public final static int N = 0xffffff;
		
		public static void main(String[] args) {
			//构造图对象
			MGrapth grapth = new MGrapth(7);
			//图数据
			String[] data = {"A","B","C","D","E","F","G"};
			//矩阵
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

