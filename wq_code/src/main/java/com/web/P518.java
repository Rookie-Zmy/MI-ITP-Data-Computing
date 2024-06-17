package com.web;

public class P518 {
    public static void main(String[] args) {
        P518 solution = new P518();

        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        int amount2 = 3;
        int[] coins2 = {2};

        System.out.println("Test 1: " + solution.change(amount1, coins1)); // Output should be 4
        System.out.println("Test 2: " + solution.change(amount2, coins2)); // Output should be 0
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j >= coins[i]) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }

        return dp[n - 1][amount];
    }
}
