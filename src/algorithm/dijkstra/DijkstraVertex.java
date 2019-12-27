package algorithm.dijkstra;

/**
 * Dijkstra 顶点类
 * 
 * @author ziker
 *
 */
public class DijkstraVertex {
	private String value;
	// 标记访问过没
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
