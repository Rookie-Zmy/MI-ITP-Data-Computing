package com.web;

import java.util.ArrayList;
import java.util.List;

public class P491 {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    private void backtracking(int[] nums, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path)); // 直接将路径添加到结果列表中
        }

        int[] used = new int[201];
        int lastAdded = start > 0 ? nums[start - 1] : Integer.MIN_VALUE; // 保存路径最后一个元素
        for (int i = start; i < nums.length; i++) {
            if (nums[i] < lastAdded || used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        P491 solution = new P491();
        int[] nums = {4, 4, 3, 2, 1};
        List<List<Integer>> subsequences = solution.findSubsequences(nums);
        for (List<Integer> subsequence : subsequences) {
            System.out.println(subsequence);
        }
    }
}

