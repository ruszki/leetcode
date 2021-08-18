package com.ruszki.leetcode.cgnibt;

class Solution {
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return goodNodes(root.left, root.val) + goodNodes(root.right, root.val) + 1;
    }

    private int goodNodes(TreeNode root, int previousMaxValue) {
        if (root == null) {
            return 0;
        }

        int maxValue = Math.max(previousMaxValue, root.val);
        boolean isGoodNode = root.val >= previousMaxValue;

        return goodNodes(root.left, maxValue) + goodNodes(root.right, maxValue) + (isGoodNode ? 1 : 0);
    }
}
