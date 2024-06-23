package com.xiaomi.computing.lizi;

/**
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。

 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-23 09:46
 **/
public class p0623 {
    public boolean detectCapitalUse(String word) {
        String[] s = word.split(" ");
        for (String s1 : s) {
            char[] chars = s1.toCharArray();
            if(chars.length==1)
                continue;
            boolean isCapital = false,isAllCapital=false;
            if('A'<=chars[0] && 'Z'>=chars[0]) isCapital=true;
            if(isCapital && 'A'<=chars[1] && 'Z'>=chars[1]) isAllCapital = true;
            else if(!isCapital && 'A'<=chars[1] && 'Z'>=chars[1]) return false;
            for(int i=2;i<chars.length;i++){
                //大写
                if(!isAllCapital && ('A'<=chars[i] && 'Z'>=chars[i]))
                    return false;
                else if(isAllCapital && !('A'<=chars[i] && 'Z'>=chars[i]))
                    return false;
            }
        }
        return true;
    }
}
