//最大字符串配对数目
// 给你一个下标从 0 开始的数组 words ，数组中包含 互不相同 的字符串。
// 如果字符串 words[i] 与字符串 words[j] 满足以下条件，我们称它们可以匹配：
// 字符串 words[i] 等于 words[j] 的反转字符串。
// 0 <= i < j < words.length
// 请你返回数组 words 中的 最大 匹配数目。
// 注意，每个字符串最多匹配一次。
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        List<String> list=new ArrayList<>();
        int n=words.length;
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if (words[i].charAt(0) == words[j].charAt(1) && words[i].charAt(1) == words[j].charAt(0)){
                    count++;
                }
            }
        }
        return count;
    }
}