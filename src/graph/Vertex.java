package graph;

/**
 * ������
 * 
 * @author ziker
 *
 */
public class Vertex {
	private String value;
	//��Ƿ��ʹ�û
	public boolean visited = false;

	public Vertex() {
	}

	public Vertex(String value) {
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
		return value;
	}
}
