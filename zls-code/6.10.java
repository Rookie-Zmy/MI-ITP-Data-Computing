class Solution {
    //救生艇
//     给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。

// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。

// 返回 承载所有人所需的最小船数 。
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {  
            return 0;
        }
        int i = 0; 
        int j = people.length - 1;
        int res = 0;
        Arrays.sort(people);  
        while (i <= j) { 
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
            res++;
        }
        return res;
    }
}
