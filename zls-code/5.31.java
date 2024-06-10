class Solution {
<<<<<<< HEAD
=======
    //找出缺失和重复的数字  给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n2] 范围内。除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
任务是找出重复的数字a 和缺失的数字 b 。返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
