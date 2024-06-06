package com.xiaomi.computing.lizi;

/**
 * 给你两个正整数 n 和 limit 。
 * 请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的 总方案数 。
 * 示例 1：
 * 输入：n = 5, limit = 2
 * 输出：3
 * 解释：总共有 3 种方法分配 5 颗糖果，且每位小朋友的糖果数不超过 2 ：(1, 2, 2) ，(2, 1, 2) 和 (2, 2, 1) 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-01 10:31
 **/
public class p0601 {
    /**
     * 可以想到用三重循环 或者回溯法，这里有数学规律 用组合数学的方法
     * 组合数学
     * 所有方案数： 把n个求放入3个有区别的盒子，允许空盒，也就是把n个球中间选两个位置放隔板，隔板可以放最前最后，也就是c（n+2,2)
     * 最后的结果是所有方案数-至少一个小朋友分到超过limit的方案数
     * 至少一个小朋友分到超过limit：给第一个小朋友limit+1个糖果，剩下的n-(limit+1)个糖果再分给三个小朋友；同样可以选第二个第三个。方案数就是 3*c（n-(limit+1)+2,2).
     *                          但是这里重复了计算至少两个小朋友分到的超过limit，要减一个回去
     *                          至少两个分到limit+1，同样 3*c(n-2(limit+1)+2,2),重复计算三个都超过limit的，减一个还回去
     *                              c(n-3*(limit+1)+2,2) (不用乘3了，这回都超过limit没区别）
     *                          也就是至少一个超过limit的方案数是 ： 3*c（n-(limit+1)+2,2)-（3*c(n-2(limit+1)+2,2)-c(n-3*(limit+1)+2,2)）
     * 最后的方案数：c（n+2,2)  - （3*c（n-(limit+1)+2,2)-（3*c(n-2*(limit+1)+2,2)-c(n-3*(limit+1)+2,2)））
     * @param n
     * @param limit
     * @return
     */
    public int distributeCandies(int n, int limit) {
        //return c2(n+2)-(3*c2(n-(limit+1)+2)-(3*c2(n-2*(limit+1)+2)-c2(n-3*(limit+1)+2)));
        back(n,limit,0);
        return res;
    }
    public int c2(int n){
        return n>1?n*(n-1)/2:0;

    }

    int res=0;

    /**
     * 题目的数据量小 用回溯同样可以A
     * 对每个小朋友分0-limit个糖果，分完之后如果糖果不剩了，就是符合条件的，方案数+1
     * @param n
     * @param limit
     * @param index
     */
    public void back(int n,int limit,int index){
        if(3==index){
            if(n==0)
                res++;
            return;
        }
        for(int i=0;i<=Math.min(n,limit);i++){
            back(n-i,limit,index+1);
        }
    }
}
