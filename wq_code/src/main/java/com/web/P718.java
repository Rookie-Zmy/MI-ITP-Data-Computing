package com.web;

public class P718 {
    public int findLength(int[] nums1, int[] nums2) {
        int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        P718 solution = new P718();

        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println("Length of longest common subarray: " + solution.findLength(nums1, nums2)); // Output: 3

        int[] nums3 = {0, 0, 0, 0, 0};
        int[] nums4 = {0, 0, 0, 0, 0};
        System.out.println("Length of longest common subarray: " + solution.findLength(nums3, nums4)); // Output: 5

        // Test case 3
        int[] nums5 = {1, 2, 3, 4, 5};
        int[] nums6 = {6, 7, 8, 9, 10};
        System.out.println("Length of longest common subarray: " + solution.findLength(nums5, nums6)); // Output: 0
    }
}
