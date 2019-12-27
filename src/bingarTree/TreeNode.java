package bingarTree;

public class TreeNode {
	// 节点的权
	private int value;
	// 节点的度
	private TreeNode leftNode;
	private TreeNode rightNode;

	public TreeNode(int value) {
		this.value = value;
	}

	public void setleftNode(TreeNode leftNode) {
		this.leftNode = leftNode;
	}

	public void setrightNode(TreeNode rightNode) {
		this.rightNode = rightNode;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public TreeNode getLeftNode() {
		return leftNode;
	}

	public TreeNode getRightNode() {
		return rightNode;
	}

	public int getValue() {
		return value;
	}

	// 前序遍历，先自己，然后左节点，然后右节点，对于所有子树而言也是这样的
	public void frontShow() {
		System.out.println(value);
		if (leftNode != null) {
			leftNode.frontShow();
		}
		if (rightNode != null) {
			rightNode.frontShow();
		}
	}

	// 中序遍历，先左节点，然后自己，然后右节点，对于所有子树也是这样
	public void midShow() {
		if (leftNode != null) {
			leftNode.midShow();
		}
		System.out.println(value);
		if (rightNode != null) {
			rightNode.midShow();
		}
	}

	// 后序遍历，先左节点，然后右节点，然后自己，对于所有子树也是这样
	public void afterShow() {
		if (leftNode != null) {
			leftNode.afterShow();
		}
		if (rightNode != null) {
			rightNode.afterShow();
		}
		System.out.println(value);
	}

	// 前序查找，先找自己，然后找左树，最后找右树，中序查找及后序查找与遍历同理
	public TreeNode frontSearch(int searchValue) {
		TreeNode target = null;
		if (value == searchValue) {
			target = this;
		} else {
			if (leftNode != null) {
				target = leftNode.frontSearch(searchValue);
			}
			if (target == null && rightNode != null) {
				target = rightNode.frontSearch(searchValue);
			}
		}
		return target;
	}

	// 删除一颗子树
	public void delete(int deleteValue) {
		if (leftNode != null && leftNode.getValue() == deleteValue) {
			leftNode = null;
			return;
		} else if (rightNode != null && rightNode.getValue() == deleteValue) {
			rightNode = null;
			return;
		}
		if (leftNode != null) {
			leftNode.delete(deleteValue);
		}
		if (rightNode != null) {
			rightNode.delete(deleteValue);
		}
	}

}
