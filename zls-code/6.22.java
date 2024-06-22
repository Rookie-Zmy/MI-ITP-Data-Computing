//最大二进制奇数
// 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。
// 你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
// 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
// 注意 返回的结果字符串 可以 含前导零。
class Solution {
    public String maximumOddBinaryNumber(String s) {
        int ones = 0;
        int zeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ones++;
            } else {
                zeros++;
            }
        }
        if (ones == 1) {
            return "0".repeat(zeros) + "1";
        }
        return "1".repeat(ones - 1) + "0".repeat(zeros) + "1";
    }
}