package sortAlgorithm;

import java.util.ArrayList;

/**
 * 练习堆排序算法
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

		// 冒泡排序
		long startOne = System.currentTimeMillis();
		bubbleSort(temp1);
		long endOne = System.currentTimeMillis();

		// 选择排序
		long startTwo = System.currentTimeMillis();
		SelectionSort(temp2);
		long endTwo = System.currentTimeMillis();

		// 堆排序
		long startThree = System.currentTimeMillis();
		sort(temp3);
		long endThree = System.currentTimeMillis();

		String s1 = "数据生成时间:" + (end - start) + "\t";
		String s2 = "冒泡排序时间:" + (endOne - startOne) + "\t";
		String s3 = "选择排序时间:" + (endTwo - startTwo) + "\t";
		String s4 = "堆排序时间:" + (endThree - startThree) + "\n";
		System.out.println(s1 + s2 + s3 + s4);
	}

	/**
	 * 冒泡排序
	 * 
	 * @param data 需要排序的数组
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
	 * 选择排序
	 * 
	 * @param data 需要排序的数组
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
	 * 大顶堆的起始位置应该是当前树的最后一个非叶子节点
	 * 
	 * @param data 需要排序的数组
	 */
	public static void sort(int[] data) {
		int start = (data.length - 1) / 2;
		// 依次遍历最后一个非叶子节点向上的所有子节点
		// 这样就把当前整颗树调整成了一个大顶堆
		for (int i = start; i >= 0; i--) {
			maxHeap(data, data.length, i);
		}
		// 把当前树调整成一颗大顶堆过后需让第一个最大的值换到最后一个，
		// 然后依次向前一个位置调整成大顶堆
		for (int i = data.length - 1; i > 0; i--) {
			// 交换堆顶最大的那个值到后面去
			int temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			// 因为上面已经将整颗树调整成了一个大顶堆，并且将最小的树底调到了树根
			// 所以现在只需要调整树根就行了，在maxHelp 里面他会依次去调整他的子树
			maxHeap(data, i, 0);
		}
	}

	/**
	 * 调整过一次过后能把当前调整的一颗子树位置调整为一个大顶堆
	 * 
	 * @param data  当前排列大顶堆的数组
	 * @param size  调整的长度
	 * @param index 调整哪一颗子树，通常是当前树的最后一个非叶子节点
	 */
	public static void maxHeap(int[] data, int size, int index) {
		// 左子节点
		int leftNode = 2 * index + 1;
		// 右子节点
		int rightNode = 2 * index + 2;
		// 和两个子节点分别对比，找出最大的节点
		int max = index;
		if (leftNode < size && data[leftNode] > data[max]) {
			max = leftNode;
		}
		if (rightNode < size && data[rightNode] > data[max]) {
			max = rightNode;
		}
		// 交换当前树的最大值
		if (max != index) {
			int temp = data[index];
			data[index] = data[max];
			data[max] = temp;

			// 当前树排列成大顶推过后可能会影响刚刚排好的大顶推，需要再排一次,
			// 位置应该是调整过后max最大位置的那颗子树的位置
			maxHeap(data, size, max);
		}
	}
}
