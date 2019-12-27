package graph;

import java.util.Arrays;

/**
 * ͼ����ϰ
 * 
 * @author ziker
 *
 */
public class GraphTest {

	public static void main(String[] args) {
		// ����һ��ͼ
		Graph graph = new Graph(10);
		// ��������
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		Vertex v5 = new Vertex("E");
		// ��Ӷ���
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		Vertex v6 = new Vertex("F");
		Vertex v7 = new Vertex("G");
		Vertex v8 = new Vertex("H");
		Vertex v9 = new Vertex("I");
		Vertex v10 = new Vertex("J");
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);
		graph.addVertex(v9);
		graph.addVertex(v10);
		// ��ӱ�
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");
		graph.addEdge("B", "E");
		graph.addEdge("E", "F");
		graph.addEdge("F", "G");
		graph.addEdge("F", "J");
		graph.addEdge("H", "I");
		graph.addEdge("H", "J");
		graph.addEdge("A", "E");
		graph.addEdge("D", "H");

		for (int[] i : graph.getAdjMat()) {
			System.out.println(Arrays.toString(i));
		}

		System.out.println("\n------------------------");
		graph.dfs();
		System.out.println("\n-------------------------");
		graph.bfs();
		System.out.println("\n-------------------------");
		System.out.print("������������ݹ�:");
		graph.dfsRecursive(4);
	}

}
