//目标和
// 给你一个非负整数数组 nums 和一个整数 target 。
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum + target) % 2 != 0 || Math.abs(target) > sum) {
            return 0;
        }
        int sumP = (sum + target) / 2;
        int[] dp = new int[sumP + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = sumP; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[sumP];
    }
}