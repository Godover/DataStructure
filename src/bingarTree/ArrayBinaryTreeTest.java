package bingarTree;

/**
 * ˳��洢�Ķ�����
 * 
 * @author ziker
 *
 */
public class ArrayBinaryTreeTest {

	public static void main(String[] args) {
		int[] data = {1,2,3,4,6,8,10,15,16,19,30};
		//����һ��˳��洢������
		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(data );
		//ǰ��������нڵ�
		arrayBinaryTree.frontShow();
		System.out.println("------------------------");
		//����λ��Ϊ1�Ľڵ�
		arrayBinaryTree.frontShow(1);
	}

}
