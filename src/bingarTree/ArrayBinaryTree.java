package bingarTree;

/**
 * ˳��洢�Ķ�����
 * 
 * ˳��洢�Ķ�����ֻ�ʺ�������ȫ������ �����κ�һ��˳�������.
 * ��ڵ�: n*2+1. 
 * �ҽڵ�: n*2+2. 
 * ��ǰ�ڵ�ĸ��ڵ�: (n-1)/2.
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
	 * ǰ�����������
	 */
	public void frontShow() {
		frontShow(0);
	}

	/**
	 * ǰ�����
	 * 
	 * @param index ����������λ��
	 */
	public void frontShow(int index) {
		//��ǰ�ڵ��ֵ��Ϊ�գ�����λ��ûԽ��
		if (data == null || data.length == 0 || index >= data.length) {
			return;
		}
		//�ȴ�ӡ�Լ������ֵ
		System.out.println(data[index]);
		//��ڵ� �� �ҽڵ� ������λ��
		int leftIndex = index * 2 + 1;
		int rightIndex = index * 2 + 2;
		//û��Խ�磬��ʾ��ǰ�ڵ㻹���ӽڵ㣬���Խ����˵����ǰ�ڵ���һ��Ҷ�ӽڵ�
		if (leftIndex > data.length) {
			return;
		} else {
			//��������
			frontShow(leftIndex);
		}
		if (rightIndex > data.length) {
			return;
		} else {
			frontShow(rightIndex);
		}
	}
}
