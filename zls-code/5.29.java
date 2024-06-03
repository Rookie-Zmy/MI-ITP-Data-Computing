class Solution {
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