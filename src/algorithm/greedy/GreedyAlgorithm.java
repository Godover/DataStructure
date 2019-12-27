package algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * ̰���㷨��ϰ��
 * ���ϸ������⣬ѡ�����Ź㲥̨,�����ٵĹ㲥̨�������е���
 * �㲥̨				���ǵ���
 *	k1					������"��"�Ϻ�"��"���"
 *	k2					"����"��������"������"
 *	k3					"�ɶ�"��"�Ϻ�"��"����
 *	k4					"�Ϻ�"��"���"
 *	k5					������"��"����"
 *
 * @author ziker
 *
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		ArrayList<String> allAres = new ArrayList<>();
		String[][] keys = {
				{"����","�Ϻ�","���"},
				{"����","����","����"},
				{"�ɶ�","�Ϻ�","����"},
				{"�Ϻ�","���"},
				{"����","����"}
		};
		@SuppressWarnings("unchecked")
		ArrayList<String>[] k = new ArrayList[5];
		for (int i = 0; i < k.length; i++) {
			k[i] = new ArrayList<>(Arrays.asList(keys[i]));
			allAres.addAll(k[i]);
		}
		//��Ÿ��ǵ�����
		ArrayList<Integer> indexs = new ArrayList<>();
		while (allAres.size() > 0) {
			int maxKey = -1;
			for (int i = 0; i < k.length; i++) {
				//�� k[i] �� allAres �Ľ����������¸�ֵ�� k[i]
				k[i].retainAll(allAres);
				//�����ҵ���󸲸��ʵ��±�
				if (maxKey != -1 && k[i].size() > maxKey) {
					maxKey = i;
				} else if (maxKey == -1) {
					maxKey = i;
				}
			}
			//�����󸲸��ʵ��������±�
			indexs.add(maxKey);
			//��δ���ǵ����е�ǰѡ�еĵ����Ƴ�
			allAres.removeAll(k[maxKey]);
		}
		for (int i = 0; i < indexs.size(); i++) {
			System.out.println(Arrays.toString(keys[indexs.get(i)]));
		}
	}

}
