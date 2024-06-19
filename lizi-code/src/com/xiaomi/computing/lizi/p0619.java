package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.*;

/**
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-19 10:08
 **/
public class p0619 {
    /**
     * 先记录每个值对应的行列坐标；然后对值进行从小到大排序；然后从最小的那个值开始计算当前的最大单元格数，更新其所在行列的最大单元格数；按从小到大的顺序，小于当前值的元素的最大单元格数都已求出，也就知道当前值的行列最大单元格数。
     * <p>
     * 例如，{1: [(0, 1)], 3: [(0, 0), (1, 0)], 4: [(1, 1)]}
     * 最小值1它所在的行和列肯定没有值能跳到它的位置，所以它的最大单元格数是它自己为1，第0行和第1列最大单元格数为1。
     * 值3有两个位置，在第0行第0列，第0行上次更新为1，所以值3最大单元格数为2，更新0行0列为2。在1行0列同理。
     *
     * @param mat
     * @return
     */
    public int maxIncreasingCells(int[][] mat) {
        // 像这样存放数字和它对应出现的坐标   3: {[0, 0], [1, 0]}
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<int[]> list = map.getOrDefault(mat[i][j], new ArrayList<int[]>());
                list.add(new int[]{i, j});
                map.put(mat[i][j], list);
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int res = 0;
        for (List<int[]> value : map.values()) {
            //这个值 有对于size个位置
            int[] dp = new int[value.size()];
            for (int i = 0; i < value.size(); i++) {
                int[] index = value.get(i);
                dp[i] = Math.max(rowMax[index[0]], colMax[index[1]]) + 1;
                res = Math.max(res, dp[i]);

            }
            //不能放在一起更新 那样相当于也可以去相等的值
            for(int i=0;i<value.size();i++){
                int[] index = value.get(i);
                rowMax[index[0]] = Math.max(rowMax[index[0]], dp[i]);
                colMax[index[1]] = Math.max(colMax[index[1]], dp[i]);
            }
        }
        return res;

    }
    @Test
    public void test(){
        maxIncreasingCells(new int[][]{{3,1},{3,4}});
    }
}
