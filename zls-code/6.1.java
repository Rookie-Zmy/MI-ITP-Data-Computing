class Solution {
<<<<<<< HEAD
=======
    //给小朋友们分糖果 给你两个正整数 n 和 limit 。请你将 n 颗糖果分给 3 位小朋友，确保没有任何小朋友得到超过 limit 颗糖果，请你返回满足此条件下的总方案数 。
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
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
