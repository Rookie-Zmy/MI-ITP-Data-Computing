package com.xiaomi.computing.lizi;

import java.util.Arrays;

/**
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回 承载所有人所需的最小船数 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-10 10:21
 **/
public class p0610 {
    /**
     * 贪心
     * 一艘船尽可能载两个人 对于体重轻的没问题 对于体重重的为了尽可能多带人 给他选的搭档就要轻一点
     * 那么对体重排序 l，r分别指向最轻和最重
     *      如果people[l]+people[r]>limit，那么这个r跟谁都会超重，船数量加1，他自己走，r-1继续判断下一个小胖
     *      如果<=limit 那这艘船可以带走l和r，继续判断l+1和r-1
     * 直到都分完 l>r
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l=0,r=people.length-1;
        int res = 0;
        while (l<=r){
            if(people[l]+people[r]<=limit){
                l++;
                r--;
            }
            else {
                r--;
            }
            res++;
        }
        return res;
    }
}
