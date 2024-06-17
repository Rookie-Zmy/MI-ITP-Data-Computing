package com.web;

import java.util.Arrays;

public class P279 {
    public static void main(String[] args) {
        P279 solution = new P279();

        int n1 = 12;
        int n2 = 13;

        System.out.println("Test 1: " + solution.numSquares(n1));
        System.out.println("Test 2: " + solution.numSquares(n2));
    }

    public int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        return dp[n];
    }
}
