package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dijkstra վ�㶥����
 * 
 * @author ziker
 *
 */
public class RpDijkstraSite {
	private int value;
	// ��Ƿ��ʹ�û
	public boolean visited = false;
	public RpDijkstraSite parent;
	// ��ǰվ��������·����
	private ArrayList<String> roadName;
	
	public RpDijkstraSite(int value, String roadName) {
		this.value = value;
		this.roadName = new ArrayList<>();
		this.roadName.add(roadName);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getRoadName() {
		return roadName.get(0);
	}

	public ArrayList<String> getRoadNames() {
		return roadName;
	}

	/**
	 * ���õ�ǰվ�����·����
	 * 
	 * @param roadName
	 */
	public void setRoadName(String roadName) {
		this.roadName.add(roadName);
	}

	@Override
	public String toString() {
		return " ~��ǰվ��:["+value+"]  "+"��·:"+Arrays.toString(roadName.toArray());
	}
	
}

