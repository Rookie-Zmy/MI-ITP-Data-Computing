package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * 句子 是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 '$' 。如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个 价格 。
 * 例如 "$100"、"$23" 和 "$6" 表示价格，而 "100"、"$" 和 "$1e5 不是。
 * 给你一个字符串 sentence 表示一个句子和一个整数 discount 。对于每个表示价格的单词，都在价格的基础上减免 discount% ，并 更新 该单词到句子中。所有更新后的价格应该表示为一个 恰好保留小数点后两位 的数字。
 * 返回表示修改后句子的字符串。
 * 注意：所有价格 最多 为  10 位数字。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-18 10:05
 **/
public class p0618 {
    /**
     * 用空格划分开
     * 然后逐个判断是否是价格 然后计算折扣价 替换
     * @param sentence
     * @param discount
     * @return
     */
    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");
        double zk = 1 - (double)discount / 100;
        for (int i=0;i<words.length;i++) {
            String word = words[i];
            if(word.startsWith("$")&&word.length()>=2){
                char[] chars = word.toCharArray();
                boolean isNum=true;
                for(int j=1;j<chars.length;j++){
                    if(!(chars[j]>='0' && chars[j]<='9')){
                        isNum=false;
                        break;
                    }
                }
                if(isNum){
                    words[i]= String.format("$%.2f", Long.parseLong(word.substring(1)) * zk);
                }
            }
        }
        return String.join(" ",words);

    }
    @Test
    public void test(){
        discountPrices("there are $1 $2 and 5$ candies in the shop",50);
    }
}
