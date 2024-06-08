class Solution {
    public long minimumSteps(String s) {
        char[] num = s.toCharArray();
        long step = 0;
        long count = 0;
        String subStr;
        int index = num.length;
        for (int i = num.length - 1; i >= 0; i--) {
            if (num[i] == '1'){
                subStr = s.substring(i+1, index);
                index = i;
                count += subStr.length();
                step += count;
            }
        }
        return step;
    }
}