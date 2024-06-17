class Solution {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        long res = 0;
        long totalProfit = 0;
        Set<Integer> vis = new HashSet<>();
        Deque<Integer> dup = new ArrayDeque<>();
        for (int i = 0; i < items.length; i++){
            int profit = items[i][0];
            int category = items[i][1];
            if (i < k) {
                totalProfit += profit;
                if (!vis.add(category)) {
                    dup.push(profit);
                }
            } else if (!dup.isEmpty() && vis.add(category)) {
                totalProfit += profit - dup.pop();
            }
            res = Math.max(res, totalProfit + (long) vis.size() * vis.size());
        }
        return res;
    }
}