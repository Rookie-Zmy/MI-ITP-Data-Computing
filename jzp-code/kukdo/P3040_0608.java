class Solution {
    private int[][] memo;
    private int[] nums;

    public int maxOperations(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        memo = new int[n][n];
        for (int[] row : memo){
            Arrays.fill(row, -1);
        }
        int res1 = helper(2, n-1, nums[0] + nums[1]);
        int res2 = helper(0, n-3, nums[n-2] + nums[n-1]);
        int res3 = helper(1, n-2, nums[0] + nums[n-1]);
        return Math.max(Math.max(res1, res2), res3) + 1;
    }

    private int helper(int i, int j, int target) {
        return dfs(i, j, target);
    }

    private int dfs(int i, int j, int target) {
        if (i >= j){
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        if (nums[i] + nums[i+1] == target){
            res = Math.max(res, dfs(i+2, j, target) + 1);
        }
        if (nums[j-1] + nums[j] == target){
            res = Math.max(res, dfs(i, j-2, target) + 1);
        }
        if (nums[i] + nums[j] == target){
            res = Math.max(res, dfs(i+1, j-1, target) + 1);
        }
        memo[i][j] = res;
        return memo[i][j];
    }
}