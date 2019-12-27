package bingarTree;

/**
 * ����������ϰ
 * 
 * @author ziker
 *
 */
public class BinaryTreeTest {

	public static void main(String[] args) {
		// ����һ����
		BinaryTree binaryTree = new BinaryTree();
		TreeNode rootNode = new TreeNode(1);
		// ���ø��ڵ�
		binaryTree.setRooTreeNode(rootNode);
		TreeNode leftNode = new TreeNode(2);
		TreeNode rightNode = new TreeNode(3);
		// ���ø��ڵ�����ҽڵ�
		rootNode.setleftNode(leftNode);
		rootNode.setrightNode(rightNode);
		leftNode.setleftNode(new TreeNode(4));
		leftNode.setrightNode(new TreeNode(5));
		rightNode.setleftNode(new TreeNode(6));
		rightNode.setrightNode(new TreeNode(7));
		// ǰ�����  ˳��洢������
		binaryTree.frontShow();
		// �������
		System.out.println("-----------------------");
		binaryTree.midShow();
		// �������
		System.out.println("-----------------------");
		binaryTree.afterShow();
		// ǰ�����
		System.out.println("-----------------------");
		TreeNode frontSearch = binaryTree.frontSearch(3);
		System.out.println(frontSearch );
		//ɾ��һ������
		System.out.println("-----------------------");
		binaryTree.delete(2);
		binaryTree.frontShow();
	}
}
