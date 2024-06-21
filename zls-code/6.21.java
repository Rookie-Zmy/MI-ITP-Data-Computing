//气温变化趋势
// 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
// 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
// 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
// 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
// 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
// 即最大的 n，使得第 i~i+n 天之间，两地气温变化趋势相同
class Solution {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int maxDays = 0;
        int currentDays = 0;
        int trendA, trendB;
        for (int i = 0; i < temperatureA.length - 1; i++) {
            trendA = temperatureA[i] - temperatureA[i + 1];
            trendB = temperatureB[i] - temperatureB[i + 1];
            if (trendA > 0) trendA = 1;
            else if (trendA < 0) trendA = -1;
            else trendA = 0;
            if (trendB > 0) trendB = 1;
            else if (trendB < 0) trendB = -1;
            else trendB = 0;
            if (trendA == trendB) {
                currentDays++;
            } else {
                maxDays = Math.max(maxDays, currentDays);
                currentDays = 0;
            }
        }
        maxDays = Math.max(maxDays, currentDays);
        return maxDays;
    }
}