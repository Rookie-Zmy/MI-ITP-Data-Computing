class Solution {
    public int countBeautifulPairs(int[] nums) {
        int res = 0;
        int x;
        int y;
        for (int i = 0; i < nums.length; ++i) {
            x = nums[i];
            while (x >= 10) {
                x /= 10;
            }
            for (int j = i+1; j < nums.length; ++j) {
                y = nums[j] % 10;
                if (gcd(x, y) == 1) {
                    res++;
                }
            }
        }
        return res;
    }
    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}