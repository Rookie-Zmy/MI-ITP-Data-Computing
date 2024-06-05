public class Leetcode392 {
    public boolean isSubsequence(String t, String s) {
        if (t.isEmpty()) return true;
        int p = 0;
        int q = 0;

        while (p < s.length() && q < t.length()) {
            if (s.charAt(p) == t.charAt(q)) {
                q++;
            }
            p++;
        }

        return q == t.length();
    }
}
