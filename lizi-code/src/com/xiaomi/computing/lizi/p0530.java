package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 今天的题目和昨天的不同之处是增加了字符串长度 对时间复杂度有限制了 用昨天的第二种做法就行了
 * 今天写这个题目
 * 某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 [1, k] 之间，且数组中的数字都没有前导 0 。
 * 给你字符串 s 和整数 k 。可能会有多种不同的数组恢复结果。
 * 按照上述程序，请你返回所有可能输出字符串 s 的数组方案数。
 * 由于数组方案数可能会很大，请你返回它对 10^9 + 7 取余 后的结果。
 * 示例 1：
 * 输入：s = "1000", k = 10000
 * 输出：1
 * 解释：唯一一种可能的数组方案是 [1000]
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-05-30 10:43
 **/
public class p0530 {
    /**
     * dp[i]表示以字符 s[i−1]结尾，前面所有数字可以划分成合法数组的方案总数（dp[0] 表示空字符串）
     * 以 s=1317,k=2000为例
     * i=0的时候，dp[0]=1
     * i=1，此时可以将 1 作为一个合法的数字，这样 dp[1]=1
     * i=2，此时可以将 3 作为一个合法的数字，有dp[1]种方案，同时也可以将 13 作为一个合法的数字，最后的答案即为 dp[2]=dp[1]+dp[0]=2
     * i=3，和上面同理，将 1 作为一个合法的数字，有dp[2]种方案 表示 s[2]前面所有能构成的方案数，然后以 31 作为一个合法的数字，有dp[1]种方案 ，最后以 131 作为一个合法的数字，有dp[0]种，最后 dp[3]=dp[0]+dp[1]+dp[2]=1+1+2=4
     * i=4，同理 dp[4]=dp[0]+dp[1]+dp[2]+dp[3]=1+1+2+4=8
     * @param s
     * @param k
     * @return
     */
    public int numberOfArrays(String s, int k) {
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0]=1;
        for(int i=1;i<=length;i++){
            for(int j=i-1;j>=Math.max(0,i-10);j--){
                String substring = s.substring(j , i );
                if(!substring.equals("")&&!substring.startsWith("0") &&Long.parseLong(substring)<=k && Long.parseLong(substring)>=1)
                    dp[i]=(dp[i]+dp[j])%1000000007;
            }
        }
        return dp[length];
    }
    @Test
    public void test(){
        numberOfArrays("1317",2000);
    }
}
