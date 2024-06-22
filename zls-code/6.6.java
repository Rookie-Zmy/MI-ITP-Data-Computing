class Solution {
    //区分白球与黑球
//    桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
//
//    给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
//
//    在每一步中，你可以选择两个相邻的球并交换它们。
//
//    返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
    public long minimumSteps(String s) {
 
        int n = s.length();
 
        char[] cs = s.toCharArray();
        long res = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '1') 
                cur++;
            else {
                res += cur;
            }
        }
        return  res;
    }
}