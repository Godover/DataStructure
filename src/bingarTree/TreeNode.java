package bingarTree;

public class TreeNode {
	// �ڵ��Ȩ
	private int value;
	// �ڵ�Ķ�
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

	// ǰ����������Լ���Ȼ����ڵ㣬Ȼ���ҽڵ㣬����������������Ҳ��������
	public void frontShow() {
		System.out.println(value);
		if (leftNode != null) {
			leftNode.frontShow();
		}
		if (rightNode != null) {
			rightNode.frontShow();
		}
	}

	// �������������ڵ㣬Ȼ���Լ���Ȼ���ҽڵ㣬������������Ҳ������
	public void midShow() {
		if (leftNode != null) {
			leftNode.midShow();
		}
		System.out.println(value);
		if (rightNode != null) {
			rightNode.midShow();
		}
	}

	// �������������ڵ㣬Ȼ���ҽڵ㣬Ȼ���Լ���������������Ҳ������
	public void afterShow() {
		if (leftNode != null) {
			leftNode.afterShow();
		}
		if (rightNode != null) {
			rightNode.afterShow();
		}
		System.out.println(value);
	}

	// ǰ����ң������Լ���Ȼ���������������������������Ҽ�������������ͬ��
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

	// ɾ��һ������
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
