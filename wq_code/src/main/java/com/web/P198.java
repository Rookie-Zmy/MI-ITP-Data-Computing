package com.web;

public class P198 {

    public static void main(String[] args) {
        P198 solution = new P198();

        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Max money that can be robbed: " + solution.rob(nums1)); // Output: 4

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("Max money that can be robbed: " + solution.rob(nums2)); // Output: 12
    }
    public int rob(int[] nums) {
        int len = nums.length;

        if (len == 0) return 0;
        else if (len == 1) return nums[0];
        else if (len == 2) return Math.max(nums[0], nums[1]);

        // Use three variables to track the last three states
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        int current = 0;

        for (int i = 2; i < len; i++) {
            current = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

}
