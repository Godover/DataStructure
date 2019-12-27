package bingarTree;

/**
 * 顺序存储的二叉树
 * 
 * @author ziker
 *
 */
public class ArrayBinaryTreeTest {

	public static void main(String[] args) {
		int[] data = {1,2,3,4,6,8,10,15,16,19,30};
		//创建一颗顺序存储二叉树
		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(data );
		//前序遍历所有节点
		arrayBinaryTree.frontShow();
		System.out.println("------------------------");
		//遍历位置为1的节点
		arrayBinaryTree.frontShow(1);
	}

}
