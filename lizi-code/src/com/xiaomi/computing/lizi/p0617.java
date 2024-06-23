package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-17 10:08
 **/
public class p0617 {
    /**
     * 判断strs[i] 在数组中的 最长的特殊序列 也就是判断str[i]是否是数组其他字符串里的字串
     * 不用去判断strs[i]的字串了 因为越长越不容易成为别人的字串 如果str[i]都已经是别人的字串了 那它的字串就更是了
     * 判断字串的方法 双指针
     *
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (o1, o2) -> -(o1.length()-o2.length()));
        for(int i=0;i<strs.length;i++){
            boolean flag = true;
            for(int j=0;j<strs.length;j++){
                if(i!=j && isSubString(strs[i],strs[j])){
                    flag=false;
                    break;
                }
            }
            //排序了  那么先找到的一定是最长的 直接返回
            if(flag)
                return strs[i].length();
        }
        return -1;
    }
    public boolean isSubString(String s,String t){
        if(s.length()>t.length())
            return false;
        int i=0,j=0;
        while (i<s.length() && j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else
                j++;
        }
        //指针移动到末尾说明是字串
        return i==s.length();
    }
    @Test
    public void test(){
        findLUSlength(new String[]{"aaa","aaa","aa"});
        //findLUSlength(new String[]{"aabbcc", "aabbcc","bc","bcc","aabbccc"});
    }
}
