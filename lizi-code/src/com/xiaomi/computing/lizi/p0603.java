package com.xiaomi.computing.lizi;

import org.junit.Test;

/**
 * 排排坐，分糖果。
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-03 17:51
 **/
public class p0603 {
    /**
     * 求根公式算
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        //一共可以分m次 用8.0扩大范围
        int x = (int) ((Math.sqrt(8.0 * candies + 1) - 1) / 2);
        //每个人可以分到i次
        int epoch = x / num_people;
        //前extra个人可以再分一次
        int extra = x % num_people;
        for(int i=0;i<num_people;i++){
            res[i]=((i+1+(i+1)+(epoch-1)*num_people)*epoch/2);
            if(i<extra)
                res[i]+=epoch*num_people+i+1;
            if(i==extra)
                res[i]+=candies-(x+1)*x/2;
        }
        return res;

    }
    @Test
    public void test(){
        distributeCandies(1000000000
                ,1000);
    }
}
