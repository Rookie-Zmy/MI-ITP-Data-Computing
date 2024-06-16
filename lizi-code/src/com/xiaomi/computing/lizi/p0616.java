package com.xiaomi.computing.lizi;

/**
 * 给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列  的长度。如果不存在，则返回 -1 。\
 * 「最长特殊序列」 定义如下：该序列为 某字符串独有的最长
 * 子序列
 * （即不能是其他字符串的子序列） 。
 * 字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。
 * 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-16 09:40
 **/
public class p0616 {
    /***
     * 一模一样就没得挑 否则选长的就行
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        return a.equals(b)?-1:Math.max(a.length(),b.length());
    }
}
