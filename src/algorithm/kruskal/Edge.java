package algorithm.kruskal;

public class Edge{
	//�ߵ����
	String start;
	//�ߵ��յ�
	String end;
	//�ߵ�Ȩֵ
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
