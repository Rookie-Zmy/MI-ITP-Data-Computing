package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.Arrays;

/**
 * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 * k 个数字的 平均值 为这些数字求和后再除以 k 。
 * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-05-27 15:27
 **/
public class p0527 {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = mean*(rolls.length+n);
        int mSum = 0;
        for (int roll : rolls) {
            mSum+=roll;
        }
        int nSum = sum - mSum;
        int[] res = new int[n];
        //n次观测的结果如果大于n*6，或者小于n 那就不可能掷出来
        if(nSum>6*n ||nSum<n)
            return new int[]{};
        //每个都赋平均值的整数部分
        Arrays.fill(res,nSum/n);
        //有多余的再补上
        if(nSum%n!=0){
            int more = nSum % n;
            for(int i=0;i<more;i++)
                res[i]++;
        }
        return res;
    }

    @Test
    public void test(){
        missingRolls(new int[]{3,2,4,3},4,2);
    }
}
