package com.xiaomi.computing.lizi;

import org.junit.Test;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
 * 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
 * 如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
 * 对于你访问的位置 i ，你可以获得分数 nums[i] 。
 * 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
 * 请你返回你能得到的 最大 得分之和。
 * 注意 ，你一开始的分数为 nums[0] 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-14 14:48
 **/
public class p0614 {
    /**
     * dp
     * dp[i+1] 为到 nums[i]的最大分数
     * 可以从之前跟nums[i]奇偶性相同的坐标比他小的最大值转过来 也可以从不同的奇偶性转过来
     * 两者得到的分数求最大
     *
     * 同时为了避免多次遍历 在遍历的时候就把当前 奇和偶 的最大分数的下标值记下来
     * 同时答案就是dp数组里最大的值
     * @param nums
     * @param x
     * @return
     */
    public long maxScore(int[] nums, int x) {
        long res = nums[0];
        int maxOdd = -1,maxEven = -1;
        long[] dp = new long[nums.length+1];
        dp[0]=-100000;
        dp[1]=nums[0];
        if(nums[0]%2==0) maxEven=0;
        else maxOdd=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]%2==0){
                dp[i+1]=Math.max(dp[maxOdd+1]-x,dp[maxEven+1])+nums[i];
                if(dp[i+1]>dp[maxEven+1])
                    maxEven=i;
            }
            else{
                dp[i+1]=Math.max(dp[maxOdd+1],dp[maxEven+1]-x)+nums[i];
                if(dp[i+1]>dp[maxOdd+1])
                    maxOdd=i;
            }
            res=Math.max(res,dp[i+1]);
        }
        return res;

    }
    @Test
    public void test(){
        maxScore(new int[]{1,1000000},3);
    }
}
