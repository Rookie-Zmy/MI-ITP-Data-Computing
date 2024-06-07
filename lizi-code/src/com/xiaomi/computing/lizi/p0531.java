package com.xiaomi.computing.lizi;

import org.junit.Test;

/**
 * 给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n2] 范围内。除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
 * 任务是找出重复的数字a 和缺失的数字 b 。
 * 返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
 * 示例 1：
 * 输入：grid = [[1,3],[2,2]]
 * 输出：[2,4]
 * 解释：数字 2 重复，数字 4 缺失，所以答案是 [2,4] 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-05-31 10:39
 **/
public class p0531 {
    /**
     * 容易想到先遍历一次 用数组或者map统计出现次数 然后再遍历一次根据出现次数得到结果
     * 这里换一种方法
     *
     * 额外添加 1,2,3,⋯ ,n*n
     *那么就有一个数出现一次，一个数出现三次，其余数均出现两次。
     *在异或操作下，一个数出现三次和出现一次是一样的，于是问题变成只出现一次的数字
     *  出现两次的数 在异或里会被抵消
     *  比如3 （011) 和 5 (101) 只出现一次 那最后的结果是110
     *  对于第1个1 分组 值为0的分一组 为1的分一组 那么3 和 5 会分到不同的组
     *  且这两个组的异或结果就是他们自己
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 
     * @param grid
     * @return
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int xorAll = 0;
        int n = grid.length;
        for (int[] nums : grid) {
            for (int num : nums) {
                xorAll^=num;
            }
        }
        //额外再加一次1-n*n 用公式计算
        xorAll ^= n % 2 > 0 ? 1 : n * n;

        int trailingZeros = Integer.numberOfTrailingZeros(xorAll);
        int[] ans = new int[2];

        //对所有数字分组
        for (int x = 1; x <= n * n; x++) {
            ans[x >> trailingZeros & 1] ^= x;
        }
        for (int[] nums : grid) {
            for (int num : nums) {
                ans[num >> trailingZeros & 1] ^= num;
            }
        }
        //ans根据下标判断不出来  哪个出现一次 哪个出现三次
        //所以只能再在原数组里遍历一次 出现过的就是添加1-n*n后出现三次的
        for (int[] nums : grid) {
            for (int num : nums) {
                if(num==ans[0])
                    return ans;
            }
        }

        return new int[]{ans[1],ans[0]};

    }
    @Test
    public void test(){
        findMissingAndRepeatedValues(new int[][]{{1,3},{2,2}});
    }
}
