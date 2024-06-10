class Solution {
    public int distributeCandies(int n, int limit) {
        int count=0;
        int[] a=new int[3];
        for(int i=0;i<3;i++){
            a[i]=0;
        }
        for(int i=0;a[0]<=limit&&a[0]<=n;i++,a[0]++){
            a[1]=0;
            for(int j=0;a[1]<=limit&&a[1]<=n;j++,a[1]++){
                a[2]=0;
                for(int k=0;a[2]<=limit&&a[2]<=n;k++,a[2]++)
                    if(a[0]+a[1]+a[2]==n)
                        count++;
            }
        }
        return count;
    }
}
