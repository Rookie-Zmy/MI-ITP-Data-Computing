public class Leetcode14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        // 以第一个字符串作为初始公共前缀的参考
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 更新公共前缀，直到该前缀适用于当前字符串
            while (strs[i].indexOf(prefix) != 0) {
                // 如果当前前缀不是 strs[i] 的前缀，则缩短前缀
                prefix = prefix.substring(0, prefix.length() - 1);
                // 优化：一旦前缀为空字符串，可以直接返回
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
