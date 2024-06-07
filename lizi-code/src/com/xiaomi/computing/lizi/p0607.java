package com.xiaomi.computing.lizi;

/**
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作：
 * 选择 nums 中的前两个元素并将它们删除。
 * 一次操作的 分数 是被删除元素的和。
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-07 11:15
 **/
public class p0607 {
    /**
     * 遍历判断能不能行就好了
     * @param nums
     * @return
     */
    public int maxOperations(int[] nums) {
        int epoch = nums.length / 2;
        int preSum = nums[0]+nums[1];
        int res=1;
        int i=2;
        while (epoch>0){
            epoch--;
            //不符合条件就结束
            if(nums[i]+nums[i+1]!=preSum)
                break;
            res++;
            i+=2;
        }
        return res;
    }
}
