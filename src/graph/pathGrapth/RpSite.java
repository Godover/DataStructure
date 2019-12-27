package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 站点类
 * 
 * @author ziker
 *
 */
public class RpSite {
	private int value;
	//标记当前顶点被访问过没有
	public boolean isVisited;
	// 当前站点所属线路名称
	private ArrayList<String> roadName;
	
	public RpSite(int value, String roadName) {
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
	
	@Override
	public boolean equals(Object obj) {
		return this.value == ((RpSite)obj).value;
	}

	
}
