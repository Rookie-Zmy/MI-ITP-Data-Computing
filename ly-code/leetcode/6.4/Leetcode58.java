public class Leetcode58 {
    public int lengthOfLastWord(String s) {
        int l=0;

        for(int i=s.length()-1;i>=0;i--){
            char ch=s.charAt(i);
            if(ch==' ' && l!=0){
                return l;
            }
            if(ch!=' '){
                l++;
            }


        }
        return l;

    }
}
