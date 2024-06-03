class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n=grid.length;
        int[] k=new int[n*n+1];
        for(int i=0;i<n*n;i++)
            k[i]=0;
        for(int[] i:grid)
            for(int j:i)
                k[j]++;
        int a=0,b=0;
        for(int i=1;i<=n*n;i++){
            if(k[i]==2)
            a=i;
            else if(k[i]==0)
            b=i;
        }
        return new int[]{a,b};
    }
}