package com.web;

import java.util.HashMap;
import java.util.Map;

public class P337 {
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        int money = root.val;
        if (root.left != null) {
            money += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            money += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(money, rob(root.left) + rob(root.right));
    }

    public int rob1(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return robAction(root, memo);
    }

    private int robAction(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null)
            return 0;
        if (memo.containsKey(root))
            return memo.get(root);
        int money = root.val;
        if (root.left != null) {
            money += robAction(root.left.left, memo) + robAction(root.left.right, memo);
        }
        if (root.right != null) {
            money += robAction(root.right.left, memo) + robAction(root.right.right, memo);
        }
        int res = Math.max(money, robAction(root.left, memo) + robAction(root.right, memo));
        memo.put(root, res);
        return res;
    }

    public int rob3(TreeNode root) {
        int[] res = robAction1(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robAction1(TreeNode root) {
        int[] res = new int[2];
        if (root == null)
            return res;

        int[] left = robAction1(root.left);
        int[] right = robAction1(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Construct a sample tree:
        //     3
        //    / \
        //   2   3
        //    \   \
        //     3   1
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        P337 solution = new P337();

        // Test rob method
        System.out.println("Max money that can be robbed (rob method): " + solution.rob(root)); // Output: 7

        // Test rob1 method
        System.out.println("Max money that can be robbed (rob1 method): " + solution.rob1(root)); // Output: 7

        // Test rob3 method
        System.out.println("Max money that can be robbed (rob3 method): " + solution.rob3(root)); // Output: 7
    }
}
