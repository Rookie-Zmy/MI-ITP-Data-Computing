import java.util.Arrays;

public class MergeSortedArray {
    public static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // 设置指针分别指向nums1和nums2的最后一个元素
            int p1 = m - 1;
            int p2 = n - 1;
            // 设置指针指向合并后数组的最后位置
            int p = m + n - 1;

            // 从后向前合并
            while (p1 >= 0 && p2 >= 0) {
                if (nums1[p1] > nums2[p2]) {
                    nums1[p] = nums1[p1];
                    p1--;
                } else {
                    nums1[p] = nums2[p2];
                    p2--;
                }
                p--;
            }

            // 如果还有剩余的nums2元素，直接复制到nums1中
            while (p2 >= 0) {
                nums1[p] = nums2[p2];
                p2--;
                p--;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1)); // 输出：[1, 2, 2, 3, 5, 6]

        // 测试用例 2
        nums1 = new int[]{1};
        m = 1;
        nums2 = new int[]{};
        n = 0;
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1)); // 输出：[1]

        // 测试用例 3
        nums1 = new int[]{0};
        m = 0;
        nums2 = new int[]{1};
        n = 1;
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1)); // 输出：[1]
    }
}