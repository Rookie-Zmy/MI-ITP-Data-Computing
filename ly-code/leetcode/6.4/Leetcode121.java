public class Leetcode121 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0]; // 初始化为第一天的价格
        int maxProfit = 0; // 初始化最大利润为0

        // 从第二天开始遍历
        for (int i = 1; i < prices.length; i++) {
            // 如果当前价格比之前记录的最低价格低，则更新最低价格
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // 计算如果今天卖出的利润
                int profit = prices[i] - minPrice;
                // 更新最大利润
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        return maxProfit; // 返回最大利润
    }
    public static void main(String[] args) {
        Leetcode121 solution = new Leetcode121();
        int[] nums = {7,6,4,3,1};

        int newLength = solution.maxProfit(nums);
        System.out.println("最大利润为: " + newLength);
    }
}
