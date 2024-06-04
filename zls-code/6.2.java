class Solution {
  //分糖果   Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
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
