package com.xiaomi.computing.lizi;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个下标从 1 开始、长度为 n 的整数数组 nums 。
 * 现定义函数 greaterCount ，使得 greaterCount(arr, val) 返回数组 arr 中 严格大于 val 的元素数量。
 * 你需要使用 n 次操作，将 nums 的所有元素分配到两个数组 arr1 和 arr2 中。在第一次操作中，将 nums[1] 追加到 arr1 。在第二次操作中，将 nums[2] 追加到 arr2 。之后，在第 i 次操作中：
 * 如果 greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr1 。
 * 如果 greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]) ，将 nums[i] 追加到 arr2 。
 * 如果 greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]) ，将 nums[i] 追加到元素数量较少的数组中。
 * 如果仍然相等，那么将 nums[i] 追加到 arr1 。
 * 连接数组 arr1 和 arr2 形成数组 result 。例如，如果 arr1 == [1,2,3] 且 arr2 == [4,5,6] ，那么 result = [1,2,3,4,5,6] 。
 * 返回整数数组 result 。
 * program: MI-ITP-Data-Computing
 * author: lizi
 * create: 2024-06-05 11:15
 **/
public class p0605 {

    /**
     * 关键是知道数组里有多少数比指定的数大
     * 转为前缀和的话 获取数组排序后的index 数组里存放每个index出现的次数
     * 指定数的index前的和就是比他小的数出现的次数
     * 树状数组 （下标从1开始）
     * 对于某个数 num，根据他在排序后的下标index，插入树状数组
     * 那么要知道有多少数比他小，就是返回下标[1,index]的和
     * @param nums
     * @return
     */
    public int[] resultArray(int[] nums) {
        int length = nums.length;
        List<Integer> a = new ArrayList<>(length); // 预分配空间
        List<Integer> b = new ArrayList<>();
        //前两个树是确定的
        a.add(nums[0]);
        b.add(nums[1]);

        int[] clone = nums.clone();
        Arrays.sort(clone);
        Bitree bitreea = new Bitree(length + 1);
        Bitree bitreeb = new Bitree(length + 1);
        //存放数字和他对应的在树状数组的下标
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<length;i++){
            map.put(clone[i],i+1);
        }
        bitreea.add(map.get(nums[0]));
        bitreeb.add(map.get(nums[1]));

        for(int i=2;i<length;i++){
            int gc1 = a.size() - bitreea.pre(map.get(nums[i]));
            int gc2 = b.size() - bitreeb.pre(map.get(nums[i]));
            if(gc1>gc2 || gc1==gc2 && a.size()<=b.size()){
                a.add(nums[i]);
                bitreea.add(map.get(nums[i]));
            }else {
                b.add(nums[i]);
                bitreeb.add(map.get(nums[i]));
            }
        }
        a.addAll(b);
        return a.stream().mapToInt(Integer::intValue).toArray();
    }
    class Bitree {
        private final int[] tree;

        public Bitree(int n) {
            tree = new int[n];
        }

        // 把下标为 i 的元素增加 1
        public void add(int i) {
            while (i < tree.length) {
                tree[i]++;
                i += i & -i;
            }
        }

        // 返回下标在 [1,i] 的元素之和
        public int pre(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i &= i - 1;
            }
            return res;
        }
    }
    @Test
    public void test(){
        resultArray(new int[]{2,1,3,3});
    }

}
