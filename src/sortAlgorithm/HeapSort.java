package sortAlgorithm;

import java.util.ArrayList;

/**
 * ��ϰ�������㷨
 * 
 * @author ziker
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		ArrayList<Integer> data = new ArrayList<>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 70000; i++) {
			data.add((int) (Math.random() * 10000 + 1));
		}
		int[] temp1 = data.stream().mapToInt(Integer::intValue).toArray();
		int[] temp2 = data.stream().mapToInt(Integer::intValue).toArray();
		int[] temp3 = data.stream().mapToInt(Integer::intValue).toArray();
		long end = System.currentTimeMillis();

		// ð������
		long startOne = System.currentTimeMillis();
		bubbleSort(temp1);
		long endOne = System.currentTimeMillis();

		// ѡ������
		long startTwo = System.currentTimeMillis();
		SelectionSort(temp2);
		long endTwo = System.currentTimeMillis();

		// ������
		long startThree = System.currentTimeMillis();
		sort(temp3);
		long endThree = System.currentTimeMillis();

		String s1 = "��������ʱ��:" + (end - start) + "\t";
		String s2 = "ð������ʱ��:" + (endOne - startOne) + "\t";
		String s3 = "ѡ������ʱ��:" + (endTwo - startTwo) + "\t";
		String s4 = "������ʱ��:" + (endThree - startThree) + "\n";
		System.out.println(s1 + s2 + s3 + s4);
	}

	/**
	 * ð������
	 * 
	 * @param data ��Ҫ���������
	 */
	private static void bubbleSort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i] > data[j]) {
					int temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
	}

	/**
	 * ѡ������
	 * 
	 * @param data ��Ҫ���������
	 */
	private static void SelectionSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] > data[j]) {
					int temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
	}

	/**
	 * �󶥶ѵ���ʼλ��Ӧ���ǵ�ǰ�������һ����Ҷ�ӽڵ�
	 * 
	 * @param data ��Ҫ���������
	 */
	public static void sort(int[] data) {
		int start = (data.length - 1) / 2;
		// ���α������һ����Ҷ�ӽڵ����ϵ������ӽڵ�
		// �����Ͱѵ�ǰ��������������һ���󶥶�
		for (int i = start; i >= 0; i--) {
			maxHeap(data, data.length, i);
		}
		// �ѵ�ǰ��������һ�Ŵ󶥶ѹ������õ�һ������ֵ�������һ����
		// Ȼ��������ǰһ��λ�õ����ɴ󶥶�
		for (int i = data.length - 1; i > 0; i--) {
			// �����Ѷ������Ǹ�ֵ������ȥ
			int temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			// ��Ϊ�����Ѿ�����������������һ���󶥶ѣ����ҽ���С�����׵���������
			// ��������ֻ��Ҫ�������������ˣ���maxHelp ������������ȥ������������
			maxHeap(data, i, 0);
		}
	}

	/**
	 * ������һ�ι����ܰѵ�ǰ������һ������λ�õ���Ϊһ���󶥶�
	 * 
	 * @param data  ��ǰ���д󶥶ѵ�����
	 * @param size  �����ĳ���
	 * @param index ������һ��������ͨ���ǵ�ǰ�������һ����Ҷ�ӽڵ�
	 */
	public static void maxHeap(int[] data, int size, int index) {
		// ���ӽڵ�
		int leftNode = 2 * index + 1;
		// ���ӽڵ�
		int rightNode = 2 * index + 2;
		// �������ӽڵ�ֱ�Աȣ��ҳ����Ľڵ�
		int max = index;
		if (leftNode < size && data[leftNode] > data[max]) {
			max = leftNode;
		}
		if (rightNode < size && data[rightNode] > data[max]) {
			max = rightNode;
		}
		// ������ǰ�������ֵ
		if (max != index) {
			int temp = data[index];
			data[index] = data[max];
			data[max] = temp;

			// ��ǰ�����гɴ��ƹ�����ܻ�Ӱ��ո��źõĴ��ƣ���Ҫ����һ��,
			// λ��Ӧ���ǵ�������max���λ�õ��ǿ�������λ��
			maxHeap(data, size, max);
		}
	}
}
