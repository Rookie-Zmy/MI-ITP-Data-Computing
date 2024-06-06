package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 * 示例 1：
 * 输入：s = "aaaa"
 * 输出：2
 * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
 * 可以证明最大长度是 2 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-05-29 11:26
 **/
public class p0529 {
    public int maximumLength(String s) {
        //用map存放特殊字串的出现次数
        Map<String,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chars[i]);
            map.put(new String(stringBuilder),map.getOrDefault(new String(stringBuilder),0)+1);
            int j=i+1;
            //存放以当前字母开头的特殊字符串长度
            while (j<chars.length && chars[j]==chars[i]){
                stringBuilder.append(chars[j]);
                map.put(new String(stringBuilder),map.getOrDefault(new String(stringBuilder),0)+1);
                j++;
            }
        }
        int res=-1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue()>=3)
                res=Math.max(res,entry.getKey().length());
        }
        return res;
    }

    /**
     * 先把连续的分段,保存每个字母的连续的段有多长，比如连续的a有三段，分别是5个a，3个，2个
     * 然后遍历每个字母的出现的连续段的长度 list。
     *  -该字母出现次数>3的连续段最长的长度怎么取呢？先排序
     *      -三种情况：直接从最长的里取，那么取出来的长度就是list.get(0)-2
     *               从最长和次长里面取，如果最长次长相等，那可以取到list.get(0)-1，如果不相等 那可以取到list.get(1)。省去判断长度，直接两者取小就可以。
     *               从最长 次长 第三长里面分别取：那可以取到list.get(2)
     *               tips:为了防止只有一种长度，可以往list里面加两个0。
     * @param s
     * @return
     */
    public int maximumLength1(String s) {
        //在数组中保存 conCharLen[0] = [1,2,4]
        //意思就是连续的a串长度有1，2，4
        List<Integer>[] conCharLen = new ArrayList[26];
        Arrays.setAll(conCharLen, i->new ArrayList<>());
        char[] chars = s.toCharArray();
        int count=0;
        for(int i=0;i<chars.length;i++){
            count++;
            if(i==s.length()-1 || chars[i+1]!=chars[i]){
                conCharLen[chars[i]-'a'].add(count);
                count=0;
            }
        }
        int res=0;
        for (List<Integer> list : conCharLen) {
            //没有出现过这个字母 跳过吧
            if(list.size()==0)
                continue;
            list.add(0);
            list.add(0);
            Collections.sort(list, (o1,o2)->-Integer.compare(o1,o2));
            int min = Math.min(list.get(0) - 1, list.get(1));
            res=Math.max(res,Math.max(list.get(0)-2,Math.max(min,list.get(2))));
        }
        return res==0?-1:res;
    }

    @Test
    public void test(){
        maximumLength("aaaa");
    }
}
