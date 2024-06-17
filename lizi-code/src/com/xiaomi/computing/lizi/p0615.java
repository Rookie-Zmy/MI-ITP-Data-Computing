package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.Arrays;

/**

 * 排序后，选出的区间是连续的，我们只需考虑最左边的区间 [x−k,x+k]和最右边的区间 [y−k,y+k]，如果这两个区间的交集不为空，那么选出的这些区间的交集不为空。也就是说，要满足
 * x+k≥y−k
 * y−x≤2k
 * 使用滑动窗口的方法来解决
 * 令某连续子数组的右端点为 i，左端点为 j
 * 初始时都为 0，依次枚举右端点 i：为了使 nums[i]−nums[j]≤2k，我们不断地右移左端点 j 直到 nums[i]−nums[j]≤2k成立
 * 那么右端点 i 对应的最长连续子数组的长度为 i−j+1。
 * 最后返回这些长度的最大值为数组的最大美丽值。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-15 11:05
 **/
public class p0615 {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 1;
        int left = 0,right = 0;
        //枚举右端点
        for (; right < nums.length; right++) {
            //找到最小的左端点
            while (nums[right] - nums[left] > k * 2) {
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;

    }
    @Test
    public void test(){
        maximumBeauty(new int[]{13,46,71},29);
    }
}
