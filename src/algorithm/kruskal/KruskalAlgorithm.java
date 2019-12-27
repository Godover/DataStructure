package algorithm.kruskal;

/**
 * 克鲁斯卡尔算法求最小生成树 解决公交问题
 * 克鲁斯卡尔算法的核心思想是尽可能的贪心算法去找最小的边，但是不能够形成回路
 * 
 * @author ziker
 *
 */
public class KruskalAlgorithm {
	final static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
			String[] vertex = {"A","B","C","D","E","F","G"};
			// 邻接矩阵
			int[][] maritx = {
					    /* 	A 		B 		C 		D 		E 		 F 		G*/
				/*A*/{	0,		12,	INF,	INF,	INF,	16,	14},
				/*B*/{	12,	0,		10,	INF,	INF,	7,		INF},
				/*C*/{	INF,	10,	0,		3,		5,		6,		INF},
				/*D*/{	INF,	INF,	3,		0,		4,		INF,	INF},
				/*E*/{	INF,	INF,	5,		4,		0,		2,		8},
				/*F*/{	16,	7,		6,		INF,	2,		0,		9},
				/*G*/{	14,	INF,	INF,	INF,	8,		9,		0}};
			MGrapth grapth = new MGrapth(vertex, maritx);
			grapth.kruskal();
	}

}
