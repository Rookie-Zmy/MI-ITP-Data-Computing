package com.web;

public class P459 {
    public boolean repeatedSubstringPattern(String s) {
        if (s.isEmpty()) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        return next[len] > 0 && len % (len - next[len]) == 0;
    }

    public static void main(String[] args) {
        P459 solution = new P459();
        System.out.println(solution.repeatedSubstringPattern("abab")); // true
        System.out.println(solution.repeatedSubstringPattern("aba"));  // false
        System.out.println(solution.repeatedSubstringPattern("abcabcabcabc")); // true
    }
}
