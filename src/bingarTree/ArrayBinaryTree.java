package bingarTree;

/**
 * 顺序存储的二叉树
 * 
 * 顺序存储的二叉树只适合用于完全二叉树 对于任何一颗顺序二叉树.
 * 左节点: n*2+1. 
 * 右节点: n*2+2. 
 * 当前节点的父节点: (n-1)/2.
 * 
 * @author ziker
 *
 */
public class ArrayBinaryTree {
	private int[] data;

	public ArrayBinaryTree(int[] data) {
		this.data = data;
	}
	
	/**
	 * 前序遍历整颗树
	 */
	public void frontShow() {
		frontShow(0);
	}

	/**
	 * 前序遍历
	 * 
	 * @param index 遍历的子树位置
	 */
	public void frontShow(int index) {
		//当前节点的值不为空，并且位置没越界
		if (data == null || data.length == 0 || index >= data.length) {
			return;
		}
		//先打印自己本身的值
		System.out.println(data[index]);
		//左节点 和 右节点 的索引位置
		int leftIndex = index * 2 + 1;
		int rightIndex = index * 2 + 2;
		//没有越界，表示当前节点还有子节点，如果越界了说明当前节点是一个叶子节点
		if (leftIndex > data.length) {
			return;
		} else {
			//遍历子树
			frontShow(leftIndex);
		}
		if (rightIndex > data.length) {
			return;
		} else {
			frontShow(rightIndex);
		}
	}
}
