class Solution {
//    回文数
//    给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
//    回文数
//    是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
//    例如，121 是回文，而 123 不是。
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
    
        String numStr = String.valueOf(x);
        int left = 0, right = numStr.length() - 1;
        
        while (left < right) {
            if (numStr.charAt(left) != numStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}