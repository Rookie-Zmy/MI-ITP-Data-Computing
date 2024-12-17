class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans=new int[num_people];
        for(int i=0;i<num_people;i++)
            ans[i]=0;
        int i=0,candy=1;
        while(true){
            if(candies<=candy){
                ans[i%num_people]+=candies;
                break;
            }else{
                ans[i%num_people]+=candy;
            }
            candies-=candy;
            candy++;
            i++;
        }
        return ans;
    }
}