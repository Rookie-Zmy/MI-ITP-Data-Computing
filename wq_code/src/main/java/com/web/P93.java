package com.web;

import java.util.ArrayList;
import java.util.List;
public class P93 {
    List<String> result = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) return result; // 提前剪枝
        restoreIpAddressesHandler(s, 0, 0);
        return result;
    }

    private void restoreIpAddressesHandler(String s, int start, int number) {
        if (start == s.length() && number == 4) {
            result.add(stringBuilder.toString());
            return;
        }
        if (start == s.length() || number == 4) {
            return;
        }

        for (int i = start; i < s.length() && i - start < 3; i++) {
            String segment = s.substring(start, i + 1);
            if (isValid(segment)) {
                stringBuilder.append(segment);
                if (number < 3) {
                    stringBuilder.append(".");
                }
                restoreIpAddressesHandler(s, i + 1, number + 1);
                stringBuilder.setLength(stringBuilder.length() - segment.length() - (number < 3 ? 1 : 0));
            }
        }
    }

    private boolean isValid(String segment) {
        if (segment.length() > 1 && segment.charAt(0) == '0') return false;
        int num = Integer.parseInt(segment);
        return num >= 0 && num <= 255;
    }

    public static void main(String[] args) {
        P93 solution = new P93();
        String testInput = "25525511135";
        List<String> ipAddresses = solution.restoreIpAddresses(testInput);
        System.out.println("Possible IP addresses for \"" + testInput + "\": " + ipAddresses);

        // 测试用例
        testInput = "0000";
        ipAddresses = solution.restoreIpAddresses(testInput);
        System.out.println("Possible IP addresses for \"" + testInput + "\": " + ipAddresses);

        testInput = "1111";
        ipAddresses = solution.restoreIpAddresses(testInput);
        System.out.println("Possible IP addresses for \"" + testInput + "\": " + ipAddresses);

        testInput = "010010";
        ipAddresses = solution.restoreIpAddresses(testInput);
        System.out.println("Possible IP addresses for \"" + testInput + "\": " + ipAddresses);

        testInput = "101023";
        ipAddresses = solution.restoreIpAddresses(testInput);
        System.out.println("Possible IP addresses for \"" + testInput + "\": " + ipAddresses);
    }
}

