package algorithm.prim;

import java.util.Arrays;

/**
 * 创建最小生成树
 * 
 * @author ziker
 *
 */
public class MiniTree {

	/**
	 * 生成图
	 * 
	 * @param grapth 图对象
	 * @param maritx 邻接矩阵
	 * @param data   图数据
	 */
	public void createGrapth(MGrapth grapth, int[][] maritx, String[] data) {
		grapth.maritx = maritx;
		grapth.data = data;
	}

	/**
	 * 打印图数据
	 * 
	 * @param grapth 图对象
	 */
	public void showGrapth(MGrapth grapth) {
		for (int[] link : grapth.maritx) {
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * 普利姆算法
	 * 
	 * @param grapth      图对象
	 * @param startVertex 起始点
	 */
	public void prim(MGrapth grapth, int startVertex) {
		boolean[] visited = new boolean[grapth.vertexs];
		visited[startVertex] = true;
		// h1 h2 用于记录六次循环中最小边的下标
		int h1 = -1;
		int h2 = -1;
		// 用于记录总和
		int sum = 0;
		// 生成的 ( n -1 ) 线路，所以从 1 开始
		for (int k = 1; k < grapth.vertexs; k++) {
			// 当前最下距离，需要在每次循环就重置，
			int minWeight = PrimAlgorithm.N;
			// 假设这是被访问过的节点。
			for (int i = 0; i < grapth.vertexs; i++) {
				// 假设这是没有被访问过的节点
				for (int j = 0; j < grapth.vertexs; j++) {
					// 用这个循环 visited[i] == true 就成立了上面第一个假设，visited[j] == false 就成立了上面第二个假设，
					// 思想就是遍历当前已经访问过的点的所有邻接点中没有访问过的节点，找到权值最小的一条边，
					// 记录下来，注意: 在这个if 里面不能更新visited 的值，否则就无法遍历当前点的所有邻接点了
					if (visited[i] == true && visited[j] == false && grapth.maritx[i][j] < minWeight) {
						minWeight = grapth.maritx[i][j];
						// 记录下标，i 则是访问过的点， j 是访问的哪个点
						h1 = i;
						h2 = j;
					}
				}
			}
			// 已经遍历完了当前已经访问过的点的所有邻接点，更新最小下权值的点为以访问
			visited[h2] = true;
			System.out.println("第一个节点:" + grapth.data[h1] + "\t第二个节点:" + grapth.data[h2] + "\t路径:" + minWeight);
			sum += minWeight;
		}
		System.out.println("总路径:" + sum);
	}

}
