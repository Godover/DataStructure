package algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 贪心算法练习。
 * 集合覆盖问题，选择最优广播台,用最少的广播台覆盖所有地区
 * 广播台				覆盖地区
 *	k1					“北京"，"上海"，"天津"
 *	k2					"广州"，“北京"，深圳"
 *	k3					"成都"，"上海"，"杭州
 *	k4					"上海"，"天津"
 *	k5					“杭州"，"大连"
 *
 * @author ziker
 *
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		ArrayList<String> allAres = new ArrayList<>();
		String[][] keys = {
				{"北京","上海","天津"},
				{"广州","北京","深圳"},
				{"成都","上海","杭州"},
				{"上海","天津"},
				{"杭州","大连"}
		};
		@SuppressWarnings("unchecked")
		ArrayList<String>[] k = new ArrayList[5];
		for (int i = 0; i < k.length; i++) {
			k[i] = new ArrayList<>(Arrays.asList(keys[i]));
			allAres.addAll(k[i]);
		}
		//存放覆盖的索引
		ArrayList<Integer> indexs = new ArrayList<>();
		while (allAres.size() > 0) {
			int maxKey = -1;
			for (int i = 0; i < k.length; i++) {
				//将 k[i] 和 allAres 的交集部分重新赋值给 k[i]
				k[i].retainAll(allAres);
				//依次找到最大覆盖率的下标
				if (maxKey != -1 && k[i].size() > maxKey) {
					maxKey = i;
				} else if (maxKey == -1) {
					maxKey = i;
				}
			}
			//添加最大覆盖率的索引的下标
			indexs.add(maxKey);
			//将未覆盖地区中当前选中的地区移除
			allAres.removeAll(k[maxKey]);
		}
		for (int i = 0; i < indexs.size(); i++) {
			System.out.println(Arrays.toString(keys[indexs.get(i)]));
		}
	}

}
