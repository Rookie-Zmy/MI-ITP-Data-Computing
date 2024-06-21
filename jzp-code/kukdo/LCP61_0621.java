class Solution {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int res = 0, freq = 0;
        int x = 0, y = 0;
        for (int i = 0; i < temperatureA.length - 1; ++i) {
            x = temperatureA[i+1] - temperatureA[i];
            y = temperatureB[i+1] - temperatureB[i];
            if ( (x * y > 0) || (x == 0 && y == 0)) {
                freq++;
                res = Math.max(res, freq);
            } else {
                freq = 0;
            }
        }
        return res;
    }
}