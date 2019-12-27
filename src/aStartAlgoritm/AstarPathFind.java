package aStartAlgoritm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class AstarPathFind {
	//ȥѰ�һ����Լ�Ϊ���ĵ���Χ�˸���:  ��  ��  ��  ��   ���� 	����   ����   ���� 
	public final static int[] dx = { 	0,	 	-1,	0, 	1, 	-1,	 -1, 		1,	   	  1 };
	public final static int[] dy = { -1,		 0, 	1, 	0, 	 1, 	 -1,  	   -1, 	  1 }; // ����Ȧ����1��ʾ����ͨ��
	
	/** 13*15 */
	final static public int[][] map = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 }, 
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 }, 
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
	};
	
	public static void main(String[] args) {
		// ��ʼ������յ�
		Point start = new Point(1, 1);
		Point end = new Point(10, 13);
		System.out.println(map.length+"\t"+map[0].length);
		/*
		 * ��һ�����⣺���FGH��Ҫ��ʼ���� ���ο����ϵ�ͼƬ���ֲ���Ҫ
		 */
		Stack<Point> stack = printPath(start, end);
		
		if (null == stack) {
			System.out.println("���ɴ�");
		} else {
			while (!stack.isEmpty()) {
				// ���(1,2)������������Ҫ��дtoString
				if (stack.size() == 1) {
					System.out.print(stack.pop());
				} else {
					System.out.print(stack.pop() + " -> ");
				}
			}
			System.out.println();
		}

	}

	public static Stack<Point> printPath(Point start, Point end) {

		/*
		 * ����PriorityQueue����Ϊ����ȡ�����ڵ�Ԫ��
		 */
		// ��ʼ�� openTable �� closeTable
		ArrayList<Point> openTable = new ArrayList<Point>();
		ArrayList<Point> closeTable = new ArrayList<Point>();
		openTable.clear();
		closeTable.clear();
		// ��ʼ��·��ջ
		Stack<Point> pathStack = new Stack<Point>();
		start.parent = null;
		// �õ���ת�����ã����ǵ�ǰ��չ��
		Point currentPoint = new Point(start.x, start.y);
		// closeTable.add(currentPoint);
		boolean flag = true;

		while (flag) {
			System.out.println();
			//	ȥ���Լ�Ϊ���ĵİ˸��㣺��  ��  ��  ��   ���� 	����   ����   ���� 
			for (int i = 0; i < 8; i++) {
				int fx = currentPoint.x + dx[i];
				int fy = currentPoint.y + dy[i];
				Point tempPoint = new Point(fx, fy);
				//�����ǰ��Ϊ�ϰ�������,���� X,���� Y
				if (map[fx][fy] == 1) {
					// ���ڱ߽綼��1�м��ϰ���Ҳ��1�����������ؿ���Խ����ϰ�����չ����
					// ��������ñ߽���ô fx >= map.length && fy > =map[0].length �ж�Խ������
//					System.out.println("map[fx][fy] == 1   i:-> " + i+"   fx:"+fx+"   fy:"+fy);
					continue;
				} else {
					// ��ǰ���Ѿ�Ϊ�յ���
					if (end.equals(tempPoint)) {
						flag = false;
						// ����tempPoint��������һ���˴�ʱ
						end.parent = currentPoint;
						break;
					}
					//������һ�����ѵĴ���
					if (i < 4) {
						//������������ҵĴ���
						tempPoint.G = currentPoint.G + 10;
					} else {
						//������ĸ�б�ǵĴ���
						tempPoint.G = currentPoint.G + 14;
					}
					
					//���ǵ��յ�Ĵ���
					tempPoint.H = Point.getDis(tempPoint, end);
					//���ۺ��� = ��һ���Ĵ��� + ���յ�Ĵ���
					tempPoint.F = tempPoint.G + tempPoint.H;
					
					// ��Ϊ��д��equals�����������������ֻ�ǰ�equals��Ȱ���
					// ��һ����ʹ��java��װ����Ĺؼ�
					//��ǰ����ǰ��ı������Ѿ��������ȶ��У��������д��equals���Ƚϵ�ֻ��λ�ã���û�бȽ�
					//���ۺ����� ���λ���ظ�����ȡ���ۺ������ŵ�һ��
					if (openTable.contains(tempPoint)) {
						int pos = openTable.indexOf(tempPoint);
						Point temp = openTable.get(pos);
						if (temp.F > tempPoint.F) {
							openTable.remove(pos);
							openTable.add(tempPoint);
							tempPoint.parent = currentPoint;
						}
					} else if (closeTable.contains(tempPoint)) {
						int pos = closeTable.indexOf(tempPoint);
						Point temp = closeTable.get(pos);
						if (temp.F > tempPoint.F) {
							closeTable.remove(pos);
							openTable.add(tempPoint);
							tempPoint.parent = currentPoint;
						}
					} else {
						//��ǰ�㲻�ڿ��Ŷ����У�������У�����ǰ���ڵ�
						openTable.add(tempPoint);
						tempPoint.parent = currentPoint;
					}
				}
			} // end for
			
			//��һ��ѭ����˵����ǰ����Χȫ���ϰ���û��·��
			//
			if (openTable.isEmpty()) {
				return null;
			} // ��·��
			if (false == flag) {
				break;
			} // �ҵ�·��
			//�ѵ�ǰ�ڵ�ӿ��Ŷ������Ƴ�������رն��У����ڹ�ע�˵�
			openTable.remove(currentPoint);
			closeTable.add(currentPoint);
			//�Կ��Ŷ�����������
			Collections.sort(openTable);
			//ѡ����ۺ�����С��һ��
			currentPoint = openTable.get(0);
			System.out.println(currentPoint);
		} // end while
		Point node = end;
		while (node.parent != null) {
			pathStack.push(node);
			node = node.parent;
		}
		return pathStack;
	}
}

class Point implements Comparable<Point> {

	int x;
	int y;
	Point parent;
	int F, G, H;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.F = 0;
		this.G = 0;
		this.H = 0;
	}

	@Override
	public int compareTo(Point o) {
		return this.F - o.F;
	}

	@Override
	public boolean equals(Object obj) {
		Point point = (Point) obj;
		if (point.x == this.x && point.y == this.y)
			return true;
		return false;
	}

	public static int getDis(Point p1, Point p2) {
		int dis = Math.abs(p1.x - p2.x) * 10 + Math.abs(p1.y - p2.y) * 10;
		return dis;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
/*
 * �ɹ��ˣ��������ҵ���һ�������·��ô�����������Ϊÿ��ȡ��ѵ㣬�ҵ���˼�ǿ���8��ÿѭ�����break�ˣ��е����ǲ�ͬ·�������·��
 */