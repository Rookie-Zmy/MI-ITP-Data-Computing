package com.leetcode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 * 示例 1：
 * 输入：nums = [3,5,2,6], k = 2
 * 输出：[2,6]
 * 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
 * https://leetcode.cn/problems/find-the-most-competitive-subsequence/description/
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-05-27 15:27
 **/
public class MostCompetitive {
    /**
     * 根据题意可知 最有竞争力的子序列就是尽可能小的数字
     * 那么要从第一个位置开始就尽可能小，也就是在[0,length-k]里选出最小的数字的index，然后在[index+1,length-k+1]里考虑下一位数字
     * 为了找出最小的，可以用单调队列，递增存放
     * -为什么是递增？因为后面的数字如果比前面的更小，那么前面的数字本身就先不可用还更大，就不需要考虑了。
     * 双端队列
     * @param nums
     * @param k
     * @return
     */
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int length = nums.length;
        //选出第一位
        for(int i=0;i<=length-k;i++){
            //维持队列递增
            while (deque.size()!=0 && nums[deque.peekLast()]>nums[i])
                deque.pollLast();
            deque.add(i);
        }
        int[] res = new int[k];
        res[0]=nums[deque.pollFirst()];
        for(int i=0;i<k-1;i++){
            //维持队列递增
            while (deque.size()!=0 && nums[deque.peekLast()]>nums[length-k+1+i])
                deque.pollLast();
            deque.add(length-k+1+i);
            res[i+1]=nums[deque.pollFirst()];
        }
        return res;
    }

    @Test
    public void test(){
        mostCompetitive(new int[]{2,4,3,3,5,4,9,6},4);
    }
}
