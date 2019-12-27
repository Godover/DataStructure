package algorithm.prim;

/**
 * 普利姆算法图结构
 * 
 * @author ziker
 *
 */
public class MGrapth {
	// 图的顶点数
	int vertexs;
	// 图的邻接矩阵
	int[][] maritx;
	// 图的顶点数据
	String[] data;

	public MGrapth(int vertexs) {
		this.vertexs = vertexs;
		this.maritx = new int[vertexs][vertexs];
		this.data = new String[vertexs];
	}
}
