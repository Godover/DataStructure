package bingarTree;

/**
 * 二叉树的练习
 * 
 * @author ziker
 *
 */
public class BinaryTreeTest {

	public static void main(String[] args) {
		// 创建一棵树
		BinaryTree binaryTree = new BinaryTree();
		TreeNode rootNode = new TreeNode(1);
		// 设置根节点
		binaryTree.setRooTreeNode(rootNode);
		TreeNode leftNode = new TreeNode(2);
		TreeNode rightNode = new TreeNode(3);
		// 设置根节点的左右节点
		rootNode.setleftNode(leftNode);
		rootNode.setrightNode(rightNode);
		leftNode.setleftNode(new TreeNode(4));
		leftNode.setrightNode(new TreeNode(5));
		rightNode.setleftNode(new TreeNode(6));
		rightNode.setrightNode(new TreeNode(7));
		// 前序遍历  顺序存储二叉树
		binaryTree.frontShow();
		// 中序遍历
		System.out.println("-----------------------");
		binaryTree.midShow();
		// 后序遍历
		System.out.println("-----------------------");
		binaryTree.afterShow();
		// 前序查找
		System.out.println("-----------------------");
		TreeNode frontSearch = binaryTree.frontSearch(3);
		System.out.println(frontSearch );
		//删除一颗子树
		System.out.println("-----------------------");
		binaryTree.delete(2);
		binaryTree.frontShow();
	}
}
