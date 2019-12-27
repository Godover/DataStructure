package algorithm.dijkstra;

/**
 * Dijkstra ������
 * 
 * @author ziker
 *
 */
public class DijkstraVertex {
	private String value;
	// ��Ƿ��ʹ�û
	public boolean visited = false;
	public DijkstraVertex parent;

	public DijkstraVertex() {
	}

	public DijkstraVertex(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Vertex [value=" + value + (parent == null ? "" :( ", parent="+parent.value)) + "]";
	}
}
