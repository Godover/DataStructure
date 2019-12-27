package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dijkstra 站点顶点类
 * 
 * @author ziker
 *
 */
public class RpDijkstraSite {
	private int value;
	// 标记访问过没
	public boolean visited = false;
	public RpDijkstraSite parent;
	// 当前站点所属线路名称
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
	 * 设置当前站点的线路名称
	 * 
	 * @param roadName
	 */
	public void setRoadName(String roadName) {
		this.roadName.add(roadName);
	}

	@Override
	public String toString() {
		return " ~当前站点:["+value+"]  "+"线路:"+Arrays.toString(roadName.toArray());
	}
	
}

