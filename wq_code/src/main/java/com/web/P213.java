package com.web;

public class P213 {

    public static void main(String[] args) {
        P213 solution = new P213();

        // Test cases
        int[] nums1 = {2, 3, 2};
        System.out.println("Max money that can be robbed: " + solution.rob(nums1)); // Output: 3

        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Max money that can be robbed: " + solution.rob(nums2)); // Output: 4

        int[] nums3 = {2, 7, 9, 3, 1};
        System.out.println("Max money that can be robbed: " + solution.rob(nums3)); // Output: 11
    }
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int len = nums.length;
        if (len == 1)
            return nums[0];

        return Math.max(robAction(nums, 0, len - 1), robAction(nums, 1, len));
    }

    private int robAction(int[] nums, int start, int end) {
        int prev2 = 0; // Represents dp[i-2]
        int prev1 = 0; // Represents dp[i-1]

        for (int i = start; i < end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
