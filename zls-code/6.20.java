//美丽下标对的数目
// 给你一个下标从 0 开始的整数数组 nums 。如果下标对 i、j 满足 0 ≤ i < j < nums.length ，如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，则认为 nums[i] 和 nums[j] 是一组 美丽下标对 。
// 返回 nums 中 美丽下标对 的总数目。
// 对于两个整数 x 和 y ，如果不存在大于 1 的整数可以整除它们，则认为 x 和 y 互质 。换而言之，如果 gcd(x, y) == 1 ，则认为 x 和 y 互质，其中 gcd(x, y) 是 x 和 y 的 最大公因数 。
class Solution {
    public int countBeautifulPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int firstDigitI = Character.getNumericValue(String.valueOf(nums[i]).charAt(0));
                int lastDigitJ = Character.getNumericValue(String.valueOf(nums[j]).charAt(String.valueOf(nums[j]).length() - 1));
                if (gcd(firstDigitI, lastDigitJ) == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}