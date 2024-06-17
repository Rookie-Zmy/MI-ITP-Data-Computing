package com.xiaomi.computing.lizi;

import org.junit.Test;

/**
 * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
 * 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
 * 在每一步中，你可以选择两个相邻的球并交换它们。
 * 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
 *
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-06 10:08
 **/
public class p0606 {
    /**
     * 只要计算1全在后面的下标值之和  和 现在的下标值之和
     * 两者的差值就是答案
     * @param s
     * @return
     */
    public long minimumSteps(String s) {
        char[] chars = s.toCharArray();
        long count=0;
        long relIndex=0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='1'){
                relIndex+=chars.length-1-i;
                count++;
            }
        }
        count=(count-1)*count/2;
        return relIndex-count;
    }
    @Test
    public void test(){
        minimumSteps("101");
    }
}
