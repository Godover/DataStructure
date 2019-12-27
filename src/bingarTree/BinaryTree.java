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
	 * 前序遍历
	 */
	public void frontShow() {
		if (rootNode != null) {
			rootNode.frontShow();
		}
	}

	/**
	 * 中序遍历
	 */
	public void midShow() {
		rootNode.midShow();

	}

	/**
	 * 后序遍历
	 */
	public void afterShow() {
		rootNode.afterShow();
	}
	
	/**
	 * 前序查找
	 * @param searchValue 查找的权 
	 * @return 查找到的节点
	 */
	public TreeNode frontSearch(int searchValue) {
		return rootNode.frontSearch(searchValue);
	}
		
	/**
	 * 删除一颗子树
	 * @param deleteValue 删除节点的权
	 */
	public void delete(int deleteValue) {
		if (rootNode.getValue() == deleteValue) {
			rootNode = null;
		} else {
			rootNode.delete(deleteValue);
		}
	}

}
