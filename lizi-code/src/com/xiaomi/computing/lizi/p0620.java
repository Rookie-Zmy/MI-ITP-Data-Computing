package com.xiaomi.computing.lizi;

/**
 *给你一个下标从 0 开始的整数数组 nums 。如果下标对 i、j 满足 0 ≤ i < j < nums.length ，如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，则认为 nums[i] 和 nums[j] 是一组 美丽下标对 。
 * 返回 nums 中 美丽下标对 的总数目。
 * 对于两个整数 x 和 y ，如果不存在大于 1 的整数可以整除它们，则认为 x 和 y 互质 。换而言之，如果 gcd(x, y) == 1 ，则认为 x 和 y 互质，其中 gcd(x, y) 是 x 和 y 的 最大公因数 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-20 09:31
 **/
public class p0620 {
    /**
     * 对于某个数，判断他前面的数字是否符合美丽下标的要求
     * 先判断这个数对1-9的互质情况
     * 再根据前面数字出现的1-9次数 得到答案
     * 前面数出现的1-9次数可以同时在遍历里得到
     * @param nums
     * @return
     */
    public int countBeautifulPairs(int[] nums) {
        //存放最高位情况
        int[] hnum = new int[10];
        char c = (nums[0] + "").charAt(0);
        hnum[c-'0']++;
        int res=0;
        for(int i=1;i<nums.length;i++){
            //对于某个数nums[i]的最后一位 判断它对于1-9之间的数j是否互质
            for(int j=1;j<10;j++){
                //互质就加上前面数字中最高位为j的个数
                if(gcd(nums[i]%10,j)==1)
                  res+=hnum[j];
            }
            //统计最高位的情况
            char temp = (nums[i] + "").charAt(0);
            hnum[temp-'0']++;
        }
        return res;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
