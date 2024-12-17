class Solution {
    //相同分数的最大操作数目Ⅰ
//    给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作：
//
//    选择 nums 中的前两个元素并将它们删除。
//    一次操作的 分数 是被删除元素的和。
//
//    在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
//
//    请你返回按照上述要求 最多 可以进行的操作次数。
    public int maxOperations(int[] nums) {
        int count = 1;
        int score = nums[0] + nums[1];
        for (int i = 2; i < nums.length;) {
            if (i + 1 < nums.length) {
                if (score == nums[i] + nums[i + 1]) {
                    count++;
                    i += 2;
                    continue;
                }
            }
            break;
        }
        return count;
    }
}