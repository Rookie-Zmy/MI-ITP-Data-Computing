package com.web;

public class P122 {
    public static void main(String[] args) {
        P122 solution = new P122();

        int[] prices1 = {7, 1, 5, 3, 6, 4}; // Expected output: 5
        int[] prices2 = {7, 6, 4, 3, 1}; // Expected output: 0
        int[] prices3 = {1, 2, 3, 4, 5}; // Expected output: 4

        System.out.println("Test 1: " + solution.maxProfit(prices1)); // Output should be 5
        System.out.println("Test 2: " + solution.maxProfit(prices2)); // Output should be 0
        System.out.println("Test 3: " + solution.maxProfit(prices3)); // Output should be 4
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        int maxProfit = 0;
        int buyPrice = prices[0];

        for (int i = 1; i < n; i++) {
            if (prices[i] > buyPrice) {
                maxProfit += prices[i] - buyPrice;
            }
            buyPrice = prices[i];
        }

        return maxProfit;
    }
}
