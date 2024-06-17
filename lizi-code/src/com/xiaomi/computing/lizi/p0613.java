package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
 * items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
 * 现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories2 计算，其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
 * 你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
 * 用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大优雅度。
 * 注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-13 10:49
 **/
public class p0613 {
    /**
     * 按利润排序
     * 先选利润最大的k个，再从后面里挑选看看能不能替换前面的
     * 由于后面的利润肯定更小，那么要选它的话只能是前面k个有种类重复的，而当前判断的属于一个新的类，可以让distinctCategories方变大
     * 那么就选有重复的且最小的 来比较能不能换掉
     * 为了记录有重复的最小的，把它们都加入栈，因为后面入栈的在栈顶肯定都是最小的
     *
     * @param items
     * @param k
     * @return
     */
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (o1, o2) -> -Integer.compare(o1[0], o2[0]));
        long res = 0;
        long totalProfit = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        //前k个贪心选就行
        for (int i = 0; i < k; i++) {
            if (map.containsKey(items[i][1])) {
                stack.add(i);
            }
            map.put(items[i][1], map.getOrDefault(items[i][1], 0) + 1);
            totalProfit += items[i][0];
        }
        res =totalProfit+ (long)map.size() * map.size();
        for (int i = k; i < items.length; i++) {
            //只有前面有重复的种类，且当前判断的这个所属的类没有出现过 才有可能替换掉之前的
            if (!map.containsKey(items[i][1]) && stack.size() != 0) {
                Integer pop = stack.pop();
                map.put(items[pop][1], map.get(items[pop][1]) - 1);
                totalProfit=totalProfit-items[pop][0] + items[i][0];
                map.put(items[i][1], 1);
                res = Math.max(res, totalProfit+(long)map.size() * map.size());

            }
        }
        return res;
    }



    @Test
    public void test() {
        findMaximumElegance(new int[][]{{10,1},{10,1},{10,1},{10,1},{10,1},{10,1},{10,1},{10,1},{10,1},{10,1},{3,2},{3,3},{3,4},{3,5},{3,6},{3,7},{3,8},{3,9},{3,10},{3,11}}, 2);
    }
}
