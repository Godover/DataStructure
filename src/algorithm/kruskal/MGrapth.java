package algorithm.kruskal;

/**
 * 克鲁斯卡尔算法图结构
 * 
 * @author ziker
 *
 */
public class MGrapth {
	int INF = Integer.MAX_VALUE;

	int vertex;
	// 边得条数
	int edgeNumber;
	// 图的邻接矩阵
	int[][] matrix;
	// 图的顶点数据
	String[] data;

	/**
	 * 初始化
	 * 
	 * @param data
	 * @param maritx
	 */
	public MGrapth(String[] data, int[][] maritx) {
		this.vertex = data.length;
		this.matrix = maritx;
		this.data = data;
		//计算边的数量
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
		// 保存已有的最小生成树中的每个顶点在最小生成树中的终点
		int[] ends = new int[edgeNumber];
		// 保存最小生成树的集合
		Edge[] rets = new Edge[edgeNumber];
		// 获取图中所有边的集合
		Edge[] edges = getEdges();
		// 按照边的权值大小排序
		sortEdge(edges);
		// 遍历edges数组，将边添加到最小生成树中，判断是否形成回路 如果没有就添加进结果数组
		for (int i = 0; i < edges.length; i++) {
			// 找到当前边的起点和终点
			int start = getPosition(edges[i].start);
			int end = getPosition(edges[i].end);
			// 用于拿取两个点的根节点终点坐标
			int m = getEnd(ends, start);
			int n = getEnd(ends, end);
			// 这里判断终点坐标是否一样，如果一样则说明构成回路，不一样则没有构成回路
			if (m != n) {
				ends[m] = n;
				//这里不需要 ends[n] = n;  genEnd 的while 里面如果 ends[i] !=0,就把 i = ends[i],这样i还是原来的值，
				// 但是,ends[m] = n;就不一样了， 
				rets[index++] = edges[i];
			}
		}
		for (int j = 0; rets[j] != null; j++) {
			System.out.println(rets[j].toString());
		}
	}

	/**
	 * 获得下标为 i 的终点，用于判断两条线的根节点是否相同
	 * 
	 * @param ends 记录终点的数组
	 * @param i    传入的顶点的下标
	 * @return 返回的是下标为 i 的这个顶点对应的终点
	 */
	public int getEnd(int[] ends, int i) {
		while (ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}

	/**
	 * 对边的权值进行排序
	 * 
	 * @param edges
	 */
	public void sortEdge(Edge[] edges) {
		//使用选择排序对边权进行排序
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
	 * 得到所有边
	 * 
	 * @return
	 */
	public Edge[] getEdges() {
		Edge[] edges = new Edge[edgeNumber];
		int num = 0;
		// 遍历图的 邻接矩阵，找到左右边，构建数组
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
	 * 找到对应的顶点坐标
	 * 
	 * @param vertex 顶点名称
	 * @return 顶点的名称
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
	 * 打印图
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
