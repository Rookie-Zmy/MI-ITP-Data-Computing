package com.xiaomi.computing.lizi;

/**
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-22 10:11
 **/
public class p0622 {
    /**
     * 不包含长度为2或者更长的回文串  ==》 不包含长度为2或3的回文串（更长的都可以去掉头尾变成2或3）
     *  ==》 s[i]!=s[i-2] && s[i]!=s[i-1];
     * @param s
     * @param k
     * @return
     */
    public String smallestBeautifulString(String s, int k) {
        char[] chars = s.toCharArray();
        int i = chars.length-1;
        chars[i]++;
        while (i<chars.length){
            //需要进位
            if(chars[i]==k+'a'){
                if(i==0)
                    return "";
                chars[i]='a';
                chars[--i]++;
            }else if(i>0 && chars[i]==chars[i-1] || i>1 && chars[i]==chars[i-2]){  //判断前面有没有回文串
                chars[i]++;
            }else {   //判断后面有没有 后面也没有就可以返回了
                i++;
            }

        }
       return new String(chars);
    }
}
