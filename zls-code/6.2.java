class Solution {
    public int distributeCandies(int[] candyType) {
        int type=0,n=candyType.length;
        int[] k=new int[10];
        for(int i=0;i<10;i++)
            k[i]=0;
        for(int i=0;i<n;i++){
            if(k[candyType[i]]==0){
                type++;
                k[candyType[i]]=1;
            }
        }
        if(type<=n/2)
            return type;
        else
            return n/2;
    }
}