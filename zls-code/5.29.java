class Solution {
   //找出出现至少三次的最长特殊子字符串1    
    给你一个仅由小写英文字母组成的字符串 s 。如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。子字符串 是字符串中的一个连续非空字符序列。
    public int maximumLength(String s) {
        Map<String,Integer>map = new HashMap<>();
        int n = s.length();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                    String ss = s.substring(i,j);
                    if(isSpe(ss)){
                    map.put(ss,map.getOrDefault(ss,0)+1);
                    }
            }
        }
        int res = -1;
        for(String key:map.keySet()){
            if(map.get(key)>=3){
                res = Math.max(res,key.length());
            }
        }
        return res;
    }
    public boolean isSpe(String s){
        int n = s.length();
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)!=s.charAt(i+1)){
                return false;
            }
        }
        return true;
    }
}
