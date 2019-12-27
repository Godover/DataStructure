package algorithm.kruskal;

public class Edge{
	//边的起点
	String start;
	//边的终点
	String end;
	//边的权值
	int weight;
	
	public Edge(String start,String end,int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}	
	
}
