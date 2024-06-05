import java.util.Arrays;

public class Leetcode169 {
//    public int majorityElement(int[] nums) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = 0; j < nums.length - 1 - i; j++) {
//                if (nums[j] > nums[j + 1]) {
//                    int temp = nums[j];
//                    nums[j] = nums[j + 1];
//                    nums[j + 1] = temp;
//                }
//            }
//        }
//        int p=0;
//        int q=1;
//        for(int i=0;i<nums.length-1;i++){
//            while(q < nums.length && nums[p] == nums[q]){
//                q++;
//            }
//            int ll=q-p;
//            if(ll<=nums.length/2){
//                p=q;
//                q++;
//                i=q;
//
//            }else{
//                break;
//            }
//
//        }
//        return nums[p];

//    }
public int majorityElement(int[] nums) {
    Arrays.sort(nums);
    // 返回中间元素
    return nums[nums.length / 2];


}

    public static void main(String[] args) {
        Leetcode26 solution = new Leetcode26();
        int[] nums = {2,2,1,1,1,2,2};

        int newLength = solution.removeDuplicates(nums);
        System.out.println("多数元素为: " + newLength);

    }
}
