class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int len = rolls.length;
        int sum = (len+n) * mean;
        for (int roll : rolls) {
            sum -= roll;
        }
        int[] res = new int[n];
        if (sum > 6*n || sum < n){
            return new int[0];
        }
        for (int i = 0; i < n; i++) {
            res[i] = sum/(n-i);
            sum -= res[i];
        }
        if (sum%n != 0){
            res[n-1] = sum;
        }
        return res;
    }
};
