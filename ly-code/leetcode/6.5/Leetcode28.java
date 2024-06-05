public class Leetcode28 {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;  // 如果 needle 是空字符串，则总是返回 0

        int n = haystack.length();
        int m = needle.length();
        if (m > n) return -1;

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }
}
