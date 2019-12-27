package bingarTree;

public class BinaryTree {
	TreeNode rootNode;

	public void setRooTreeNode(TreeNode rooTreeNode) {
		this.rootNode = rooTreeNode;
	}

	public TreeNode getRooTreeNode() {
		return rootNode;
	}
	
	/**
	 * ǰ�����
	 */
	public void frontShow() {
		if (rootNode != null) {
			rootNode.frontShow();
		}
	}

	/**
	 * �������
	 */
	public void midShow() {
		rootNode.midShow();

	}

	/**
	 * �������
	 */
	public void afterShow() {
		rootNode.afterShow();
	}
	
	/**
	 * ǰ�����
	 * @param searchValue ���ҵ�Ȩ 
	 * @return ���ҵ��Ľڵ�
	 */
	public TreeNode frontSearch(int searchValue) {
		return rootNode.frontSearch(searchValue);
	}
		
	/**
	 * ɾ��һ������
	 * @param deleteValue ɾ���ڵ��Ȩ
	 */
	public void delete(int deleteValue) {
		if (rootNode.getValue() == deleteValue) {
			rootNode = null;
		} else {
			rootNode.delete(deleteValue);
		}
	}

}
