class Solution {
    public long minimumSteps(String s) {
        long step = 0;
        long count = 0;
        for (int i = s.length()-1; i >= 0; i--){
            if (s.charAt(i) == '1'){
                step += count;
            }
            else{
                count++;
            }
        }
        return step;
    }
}