package com.web;

public class P42 {
    public int trap(int[] height) {
        int sum = 0;
        int n = height.length;

        if (n <= 2) {
            return 0;
        }

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            if (minHeight > height[i]) {
                sum += minHeight - height[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        P42 solution = new P42();

        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Amount of water trapped: " + solution.trap(height1)); // Output: 6

        int[] height2 = {4,2,0,3,2,5};
        System.out.println("Amount of water trapped: " + solution.trap(height2)); // Output: 9
    }
}
