package com.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P46 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used; // 用于标记数字是否已经被使用

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return result;
        used = new boolean[nums.length]; // 初始化布尔数组
        backtrack(nums, path);
        return result;
    }

    public void backtrack(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) { // 如果数字已经被使用，跳过
                continue;
            }
            path.add(nums[i]);
            used[i] = true; // 标记该数字为已使用
            backtrack(nums, path);
            path.removeLast();
            used[i] = false; // 取消该数字的使用标记
        }
    }

    public static void main(String[] args) {
        P46 solution = new P46();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }
}

