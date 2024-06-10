class Solution {
<<<<<<< HEAD
=======
    //给你一个下标从 0 开始的数组 mountain 。你的任务是找出数组 mountain 中的所有 峰值。
     以数组形式返回给定数组中 峰值 的下标，顺序不限 。
     注意：
     峰值 是指一个严格大于其相邻元素的元素。
     数组的第一个和最后一个元素 不 是峰值。
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> ans = new ArrayList<>();
        int n = mountain.length;
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                ans.add(i);
            }
        }
        return ans;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 8a22dddc8517e9bbe4916cc391991ad5b2fa012f
