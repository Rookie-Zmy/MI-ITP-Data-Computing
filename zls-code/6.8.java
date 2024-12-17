class Solution {
    //相同分数的最大操作数目Ⅱ
//    给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
//
//    选择 nums 中最前面两个元素并且删除它们。
//    选择 nums 中最后两个元素并且删除它们。
//    选择 nums 中第一个和最后一个元素并且删除它们。
//    一次操作的 分数 是被删除元素的和。
//
//    在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
//
//    请你返回按照上述要求 最多 可以进行的操作次数。
    int res = 0;
    public int maxOperations(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        Set<Integer> set = new HashSet<>();
        set.add(nums[0] + nums[1]);
        set.add(nums[0] + nums[j]);
        set.add(nums[j] + nums[j - 1]);
        for (int target : set) {
            int[][] ans = new int[nums.length][nums.length];
            dfs(i, j, nums, 0, target, ans);
        }
        return res;
    }

    private void dfs(int i, int j, int[] nums, int count, int taregt, int[][] ans) {
        if (res == nums.length / 2) {
            return;
        }
        if (i >= j || ans[i][j] == 1) {
            res = Math.max(res, count);
            return;
        }
        ans[i][j] = 1;
        if (nums[i] + nums[i + 1] == taregt) {
            dfs(i + 2, j, nums, count + 1, taregt, ans);
        }

        if (nums[i] + nums[j] == taregt) {
            dfs(i + 1, j - 1, nums, count + 1, taregt, ans);
        }

        if (nums[j] + nums[j - 1] == taregt) {
            dfs(i, j - 2, nums, count + 1, taregt, ans);
        }
        res = Math.max(res, count);
    }
}
