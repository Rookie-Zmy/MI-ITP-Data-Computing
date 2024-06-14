//访问数组中的位置使分数最大
// 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
// 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
// 如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
// 对于你访问的位置 i ，你可以获得分数 nums[i] 。
// 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
// 请你返回你能得到的 最大 得分之和。
// 注意 ，你一开始的分数为 nums[0] 。
class Solution {
    public long maxScore(int[] nums, int x) {
        long odd = nums[0] % 2 != 0 ? 0 : -x, even = nums[0] % 2 != 0 ? -x : 0;
        for (int t : nums) {
            if (t % 2 != 0) {
                odd = Math.max(odd + t, even + t - x);
            }
            else {
                even = Math.max(odd + t - x, even + t);
            }
        }
        return Math.max(odd, even);
    }
}
