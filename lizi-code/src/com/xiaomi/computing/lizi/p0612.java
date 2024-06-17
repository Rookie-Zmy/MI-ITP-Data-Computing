package com.xiaomi.computing.lizi;


/**
 * 一开始，你的银行账户里有 100 块钱。
 * 给你一个整数purchaseAmount ，它表示你在一次购买中愿意支出的金额。
 * 在一个商店里，你进行一次购买，实际支出的金额会向 最近 的 10 的 倍数 取整。换句话说，你实际会支付一个 非负 金额 roundedAmount ，满足 roundedAmount 是 10 的倍数且 abs(roundedAmount - purchaseAmount) 的值 最小 。
 * 如果存在多于一个最接近的 10 的倍数，较大的倍数 是你的实际支出金额。
 * 请你返回一个整数，表示你在愿意支出金额为 purchaseAmount 块钱的前提下，购买之后剩下的余额。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-12 10:51
 **/
public class p0612 {
    /**
     * 四舍五入
     * @param purchaseAmount
     * @return
     */
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int times = purchaseAmount / 10;
        int mod = purchaseAmount % 10;
        if(mod>=5){
            return 100-((times+1)*10);
        }
        return 100-times*10;
    }

}
