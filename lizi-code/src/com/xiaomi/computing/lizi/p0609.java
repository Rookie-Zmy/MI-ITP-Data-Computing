package com.xiaomi.computing.lizi;

import java.util.Arrays;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-09 14:57
 **/
public class p0609 {
    /**
     * 戳气球的操作，发现这会导致两个气球从不相邻变成相邻，使得后续操作难以处理。于是我们倒过来看这些操作，将全过程看作是每次添加一个气球。
     * dp[l][r] 表示(l,r)内的位置全部填满气球可以得到的最大硬币数量
     * dp[l][r] = max (dp[l][k] + dp[k][r] + nums[i]*nums[j]*nums[k] ) k=i+1,...j-1  相当于k是最后一个删除的
     * 从结果往回求比较好操作
     * @param nums
     * @return
     */
    int[][] dp;
    public int maxCoins(int[] nums) {
        int length = nums.length;
        //为了省去开头和结尾的特殊判断 这次把数组复制一下 开头和结尾设为1
        int[] copyNums = new int[length + 2];
        for(int i=1;i<length+1;i++){
            copyNums[i]=nums[i-1];
        }
        copyNums[0]=1;
        copyNums[length+1]=1;
        dp = new int[length+2][length+2];
        for (int[] ints : dp) {
            Arrays.fill(ints,-1);
        }
        dfs(copyNums,0,length+1);
        return dp[0][length+1];

    }
    public int dfs(int[] nums,int left,int right){
        if(left>=right-1)
            return 0;
        if(dp[left][right]!=-1)
            return dp[left][right];
        for(int i=left+1;i<right;i++){
            dp[left][right]=Math.max(dp[left][right],dfs(nums,left,i)+dfs(nums,i,right)+nums[i]*nums[left]*nums[right]);
        }
        return dp[left][right];
    }
}
