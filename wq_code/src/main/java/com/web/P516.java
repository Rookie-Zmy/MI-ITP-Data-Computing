package com.web;

public class P516 {
    public static void main(String[] args) {
        P516 solution = new P516();

        String s1 = "bbbab";
        String s2 = "cbbd";

        System.out.println("Test 1: " + solution.longestPalindromeSubseq(s1)); // Output should be 4
        System.out.println("Test 2: " + solution.longestPalindromeSubseq(s2)); // Output should be 2
    }

    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1; // Each character is a palindrome of length 1
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
