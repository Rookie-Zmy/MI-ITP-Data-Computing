// 模拟法
class Solution {
    public int maxOperations(int[] nums) {
        int sum = nums[0] + nums[1];
        int res = 1;
        for (int i = 2; (i < nums.length-1) && (nums[i] + nums[i+1] == sum); i += 2){
            res++;
        }
        return res;
    }
}