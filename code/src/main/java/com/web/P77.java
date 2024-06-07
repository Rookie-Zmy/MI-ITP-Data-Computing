package com.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P77 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    private void combineHelper(int n, int k, int startIndex) {
        // 如果当前路径包含k个元素，将其添加到结果列表中。
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 遍历当前组合位置的候选元素。
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);              // 将当前元素添加到路径中。
            combineHelper(n, k, i + 1); // 递归调用，起始索引为下一个元素。
            path.removeLast();        // 回溯，通过移除最后添加的元素。
        }
    }

    public static void main(String[] args) {
        P77 solution = new P77();
        List<List<Integer>> combinations = solution.combine(4, 2);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }
}
