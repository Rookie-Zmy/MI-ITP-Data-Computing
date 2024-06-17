package com.web;

import java.util.Arrays;
import java.util.stream.IntStream;

public class P1005 {
    public static void main(String[] args) {
        P1005 solution = new P1005();

        // Test cases
        int[] nums1 = {4, 2, 3}; // Expected output: 9
        int k1 = 1;
        int[] nums2 = {3, -1, 0, 2}; // Expected output: 6
        int k2 = 3;
        int[] nums3 = {2, -3, -1, 5, -4}; // Expected output: 13
        int k3 = 2;

        System.out.println("Test 1: " + solution.largestSumAfterKNegations(nums1, k1)); // Output should be 9
        System.out.println("Test 2: " + solution.largestSumAfterKNegations(nums2, k2)); // Output should be 6
        System.out.println("Test 3: " + solution.largestSumAfterKNegations(nums3, k3)); // Output should be 13
    }

    public int largestSumAfterKNegations(int[] nums, int K) {
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue)
                .toArray();

        int len = nums.length;

        for (int i = 0; i < len && K > 0; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                K--;
            }
        }

        if (K > 0 && K % 2 == 1) {
            nums[len - 1] = -nums[len - 1];
        }

        return Arrays.stream(nums).sum();
    }
}
