package com.web;

import java.security.PublicKey;

public class P151{
    public String reverseWords(String s) {
        StringBuilder sb = removeSpace(s);
        reverseString(sb, 0, sb.length()-1);
        reverseEachWord(sb);
        return sb.toString();
    }
    private StringBuilder removeSpace(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end && s.charAt(start) == ' ') start++;
        while (start <= end && s.charAt(end) == ' ') end--;

        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ')) {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    public void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 0;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start;
        }
    }

    public static void main(String[] args) {
        P151 solution = new P151();
        String s1 = "  the sky   is blue  ";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: \"" + solution.reverseWords(s1) + "\"");

        String s2 = "  hello world  ";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: \"" + solution.reverseWords(s2) + "\""); // 输出 "world hello"
    }
}