package com.xiaomi.computing.lizi;

import org.junit.Test;

/**
 * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
 * 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
 * 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
 * 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
 * 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
 * 即最大的 n，使得第 i~i+n 天之间，两地气温变化趋势相同
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-21 09:47
 **/
public class p0621 {
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int length = temperatureA.length;
        int[] trendA = new int[length - 1];
        int[] trendB = new int[length - 1];
        int res=0,temp=0;
        //一次遍历 temp记录当前判断的趋势相同的个数
        for(int i=1;i<length;i++){
            trendA[i-1]=Integer.compare(temperatureA[i],temperatureA[i-1]);
            trendB[i-1]=Integer.compare(temperatureB[i],temperatureB[i-1]);
            //相等就+1 不等就清零
            if(trendA[i-1]==trendB[i-1]){
                temp++;
                res=Math.max(res,temp);
            }else {
                temp=0;
            }
        }

//        int i=0,j=0;
//        while (j<length){
//            while (j<length && trendA[j]==trendB[j]){
//                res=Math.max(res,j-i+1);
//                j++;
//            }
//            if(i==j){
//                i++;
//                j++;
//            }
//            else i=j;
//
//        }
        return res;
    }

    @Test
    public void test(){
        temperatureTrend(new int[]{21,18,18,18,31},new int[]{34,32,16,16,17});
    }
}
