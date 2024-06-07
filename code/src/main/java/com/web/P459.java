package com.web;

public class P459 {
    public boolean repeatedSubstringPattern(String s) {
        if (s.isEmpty()) return false;

        int len = s.length();
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        for (int i = 2, j = 0; i <= len; i++) {
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            if (chars[i] == chars[j + 1]) j++;
            next[i] = j;
        }

        return next[len] > 0 && len % (len - next[len]) == 0;
    }

    public static void main(String[] args) {
        P459 solution = new P459();
        System.out.println(solution.repeatedSubstringPattern("abab")); // true
        System.out.println(solution.repeatedSubstringPattern("aba"));  // false
        System.out.println(solution.repeatedSubstringPattern("abcabcabcabc")); // true

    }
}
