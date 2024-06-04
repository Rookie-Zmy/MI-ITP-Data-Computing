public class Leetcode26 {
    public int removeDuplicates(int[] nums) {
        int k=0;
        int p=1;
        while (p < nums.length) {
            if (nums[p] != nums[k]) {
                // 只有当发现不同元素时，才进行操作
                k++;
                nums[k] = nums[p];
            }
            p++;  // p 始终向前移动
        }
        return k + 1;
    }

    public static void main(String[] args) {
        Leetcode26 solution = new Leetcode26();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};

        int newLength = solution.removeDuplicates(nums);
        System.out.println("新数组的长度为: " + newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
