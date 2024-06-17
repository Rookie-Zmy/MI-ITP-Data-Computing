package com.xiaomi.computing.xcl;

import java.util.Arrays;

public class P0527 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int existSum = Arrays.stream(rolls).sum();
        int m = rolls.length;

        int expectSum = mean * (m + n);
        int missingSum = expectSum - existSum;
        if (missingSum < n || missingSum > 6 * n) {
            return new int[0];
        }

        int avg = missingSum / n;
        int remain = missingSum - avg * n;

        int[] result = new int[n];
        Arrays.fill(result, 0, remain, avg + 1);
        Arrays.fill(result, remain, n, avg);
        return result;
    }
}
