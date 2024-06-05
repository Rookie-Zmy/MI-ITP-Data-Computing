public class Leetcode125 {
    public boolean isPalindrome(String s) {
        int p=0;
        int q=s.length()-1;
        s=s.toLowerCase();
        while (p < q) {

            while (p < q && !Character.isLetterOrDigit(s.charAt(p))) {
                p++;
            }
            while (p < q && !Character.isLetterOrDigit(s.charAt(q))) {
                q--;
            }

            if (s.charAt(p) != s.charAt(q)) {
                return false;
            }
            p++;
            q--;
        }
        return true;
    }
}
