class Leetcode27 {
    public int removeElement(int[] nums, int val) {
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[k]=nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        Leetcode27 solution = new Leetcode27();
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int newLength = solution.removeElement(nums, val);
        System.out.println("新数组的长度为: " + newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

