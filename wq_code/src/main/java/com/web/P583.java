package com.web;

public class P583 {
    public int minDistance(String word1, String word2) {
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();

        int len1 = char1.length;
        int len2 = char2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (char1[i - 1] == char2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return len1 + len2 - 2 * dp[len1][len2];
    }

    public static void main(String[] args) {
        P583 solution = new P583();

        // Test case 1
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Minimum operations to convert \"" + word1 + "\" to \"" + word2 + "\": " + solution.minDistance(word1, word2)); // Output: 3

        // Test case 2
        String word3 = "intention";
        String word4 = "execution";
        System.out.println("Minimum operations to convert \"" + word3 + "\" to \"" + word4 + "\": " + solution.minDistance(word3, word4)); // Output: 5
    }
}
