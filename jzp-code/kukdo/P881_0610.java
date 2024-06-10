class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0;
        for (int i = 0, j = people.length - 1; i <= j; j--) {
            if (people[i] + people[j] <= limit){
                i++;
            }
            res++;
        }
        return res;
    }
}