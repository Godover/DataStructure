package graph.pathGrapth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * վ����
 * 
 * @author ziker
 *
 */
public class RpSite {
	private int value;
	//��ǵ�ǰ���㱻���ʹ�û��
	public boolean isVisited;
	// ��ǰվ��������·����
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
	
	@Override
	public boolean equals(Object obj) {
		return this.value == ((RpSite)obj).value;
	}

	
}
