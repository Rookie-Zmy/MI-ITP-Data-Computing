//移除字符串中的尾随零
//给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
class Solution {
    public String removeTrailingZeros(String num) {
        if (num.matches("0+")) {
            return "0";
        }
        int i = num.length() - 1;
        while (i >= 0 && num.charAt(i) == '0') {
            i--;
        }
        return num.substring(0, i + 1);
    }
}