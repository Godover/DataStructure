package aStartAlgoritm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class AstarPathFind {
	//去寻找基于自己为中心的周围八个点:  上  左  下  右   左下 	左上   右上   右下 
	public final static int[] dx = { 	0,	 	-1,	0, 	1, 	-1,	 -1, 		1,	   	  1 };
	public final static int[] dy = { -1,		 0, 	1, 	0, 	 1, 	 -1,  	   -1, 	  1 }; // 最外圈都是1表示不可通过
	
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
		// 初始化起点终点
		Point start = new Point(1, 1);
		Point end = new Point(10, 13);
		System.out.println(map.length+"\t"+map[0].length);
		/*
		 * 第一个问题：起点FGH需要初始化吗？ 看参考资料的图片发现不需要
		 */
		Stack<Point> stack = printPath(start, end);
		
		if (null == stack) {
			System.out.println("不可达");
		} else {
			while (!stack.isEmpty()) {
				// 输出(1,2)这样的形势需要重写toString
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
		 * 不用PriorityQueue是因为必须取出存在的元素
		 */
		// 初始化 openTable 和 closeTable
		ArrayList<Point> openTable = new ArrayList<Point>();
		ArrayList<Point> closeTable = new ArrayList<Point>();
		openTable.clear();
		closeTable.clear();
		// 初始化路径栈
		Stack<Point> pathStack = new Stack<Point>();
		start.parent = null;
		// 该点起到转换作用，就是当前扩展点
		Point currentPoint = new Point(start.x, start.y);
		// closeTable.add(currentPoint);
		boolean flag = true;

		while (flag) {
			System.out.println();
			//	去找自己为中心的八个点：上  左  下  右   左下 	左上   右上   右下 
			for (int i = 0; i < 8; i++) {
				int fx = currentPoint.x + dx[i];
				int fy = currentPoint.y + dy[i];
				Point tempPoint = new Point(fx, fy);
				//如果当前点为障碍物跳过,竖轴 X,横轴 Y
				if (map[fx][fy] == 1) {
					// 由于边界都是1中间障碍物也是1，，这样不必考虑越界和障碍点扩展问题
					// 如果不设置边界那么 fx >= map.length && fy > =map[0].length 判断越界问题
//					System.out.println("map[fx][fy] == 1   i:-> " + i+"   fx:"+fx+"   fy:"+fy);
					continue;
				} else {
					// 当前点已经为终点了
					if (end.equals(tempPoint)) {
						flag = false;
						// 不是tempPoint，他俩都一样了此时
						end.parent = currentPoint;
						break;
					}
					//这是走一步花费的代价
					if (i < 4) {
						//这个是上下左右的代价
						tempPoint.G = currentPoint.G + 10;
					} else {
						//这个是四个斜角的代价
						tempPoint.G = currentPoint.G + 14;
					}
					
					//这是到终点的代价
					tempPoint.H = Point.getDis(tempPoint, end);
					//估价函数 = 走一步的代价 + 到终点的代价
					tempPoint.F = tempPoint.G + tempPoint.H;
					
					// 因为重写了equals方法，所以这里包含只是按equals相等包含
					// 这一点是使用java封装好类的关键
					//当前点在前面的遍历中已经加入优先队列，这里的重写了equals，比较的只是位置，并没有比较
					//估价函数， 如果位置重复，这取估价函数最优的一个
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
						//当前点不在开放队列中，加入队列，更新前驱节点
						openTable.add(tempPoint);
						tempPoint.parent = currentPoint;
					}
				}
			} // end for
			
			//第一次循环完说明当前点周围全是障碍，没有路径
			//
			if (openTable.isEmpty()) {
				return null;
			} // 无路径
			if (false == flag) {
				break;
			} // 找到路径
			//把当前节点从开放队列中移除，加入关闭队列，不在关注此点
			openTable.remove(currentPoint);
			closeTable.add(currentPoint);
			//对开放队列升序排序
			Collections.sort(openTable);
			//选择估价函数最小的一个
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
 * 成功了，我在想找到的一定是最佳路线么，别告诉我因为每次取最佳点，我的意思是可能8次每循环完就break了，男刀这是不同路径的最佳路线
 */