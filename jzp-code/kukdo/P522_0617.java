class Solution {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        next:
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (j != i && isSubseq(strs[i], strs[j])) {
                    continue next;
                }
            }
            return strs[i].length();
        }
        return -1;
    }

    // 判断 s 是否为 t 的子序列
    private boolean isSubseq(String s, String t) {
        int i = 0;
        for (char c : t.toCharArray()) {
            if (s.charAt(i) == c && ++i == s.length()) { // 所有字符匹配完毕
                return true; // s 是 t 的子序列
            }
        }
        return false;
    }
}
