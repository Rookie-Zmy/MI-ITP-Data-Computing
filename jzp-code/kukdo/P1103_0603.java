class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int n = num_people;
        int[] ans = new int[n];
        int i = 1;
        while (candies > 0){
            ans[(i - 1) % n] += Math.min(i, candies);
            candies -= i;
            i++;
        }
        return ans;
    }
}