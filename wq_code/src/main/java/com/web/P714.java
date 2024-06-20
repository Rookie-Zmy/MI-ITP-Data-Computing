package com.web;

public class P714 {
    public static void main(String[] args) {
        P714 solution = new P714();

        int[] prices1 = {1, 3, 2, 8, 4, 9}; // Expected output: 8
        int fee1 = 2;
        int[] prices2 = {1, 3, 7, 5, 10, 3}; // Expected output: 6
        int fee2 = 3;

        System.out.println("Test 1: " + solution.maxProfit(prices1, fee1)); // Output should be 8
        System.out.println("Test 2: " + solution.maxProfit(prices2, fee2)); // Output should be 6
    }

    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }

        int[][] dp = new int[2][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            int curr = i % 2;
            int prev = (i - 1) % 2;

            dp[curr][0] = Math.max(dp[prev][0], dp[prev][1] - prices[i]);
            dp[curr][1] = Math.max(dp[prev][1], dp[prev][0] + prices[i] - fee);
        }

        return dp[(len - 1) % 2][1];
    }
}
