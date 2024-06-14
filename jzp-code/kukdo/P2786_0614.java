class Solution {
    public long maxScore(int[] nums, int x) {
        long res = nums[0];
        long[] score = {0, 0};
        int flag = nums[0] % 2;
        score[flag] = nums[0];
        score[1 - flag] = nums[0] - x;
        for (int i = 1; i < nums.length; i++){
            flag = nums[i] % 2;
            score[flag] = Math.max(score[flag] + nums[i], score[1 - flag] + nums[i] - x);
            res = Math.max(res, score[flag]);
        }
        return res;
    }
}