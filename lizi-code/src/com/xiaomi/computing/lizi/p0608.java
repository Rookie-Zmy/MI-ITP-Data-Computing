package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 * 选择 nums 中最前面两个元素并且删除它们。
 * 选择 nums 中最后两个元素并且删除它们。
 * 选择 nums 中第一个和最后一个元素并且删除它们。
 * 一次操作的 分数 是被删除元素的和。
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 * 示例 1：
 * 输入：nums = [3,2,1,2,3,4]
 * 输出：3
 * 解释：我们执行以下操作：
 * - 删除前两个元素，分数为 3 + 2 = 5 ，nums = [1,2,3,4] 。
 * - 删除第一个元素和最后一个元素，分数为 1 + 4 = 5 ，nums = [2,3] 。
 * - 删除第一个元素和最后一个元素，分数为 2 + 3 = 5 ，nums = [] 。
 * 由于 nums 为空，我们无法继续进行任何操作。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-08 10:47
 **/
public class p0608 {
    /**
     * 深度优先遍历搜索所有可能的结果：删除前两个、最后两个、一头一尾
     * 但是单纯遍历的话会超时，大概还有二十个测试用例过不去
     * 所以对遍历进行优化，防止重复计算，已经计算出结果的保存在数组里
     * dp[i][j] 表示闭区间[i][j] 上的结果
     * @param nums
     * @return
     */
    public int maxOperations(int[] nums) {
        int length = nums.length;
        dp=new int[length][length];
        for (int[] ints : dp) {
            Arrays.fill(ints,-1);
        }
        //第一个传入-1 特殊判断一下 因为刚开始可以三种随便删 不需要判断
        return dfsRmNums(nums,0,nums.length-1,-1);
    }
    int [][] dp;
    public int dfsRmNums(int[] nums,int i,int j,int sum){
        if(j-i<1)
            return 0;
        //计算过了的就不再算了 优化时间复杂度
        if(dp[i][j]!=-1)
            return dp[i][j];
        int temp=0;
        if(nums[i]+nums[i+1]==sum || sum==-1){
            temp = Math.max(temp,dfsRmNums(nums, i + 2, j,  nums[i] + nums[i + 1]));
        }
        if(nums[j]+nums[j-1]==sum || sum==-1){
            temp = Math.max(temp,dfsRmNums(nums, i, j - 2,  nums[j] + nums[j - 1]));
        }
        if(nums[j]+nums[i]==sum || sum==-1){
            temp = Math.max(temp,dfsRmNums(nums, i + 1, j - 1, nums[j] + nums[i]));
        }
        dp[i][j]=temp;
        return dp[i][j];

    }

    @Test
    public void test(){
        maxOperations(new int[]{3,2,1,2,3,4});
    }

}
