package com.ruszki.leetcode.cgnibt;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCases {
    @Test
    public void test1() {
        Integer[] nodes = new Integer[]{3,1,4,3,null,1,5};

        final Solution solution = new Solution();
        final TreeNode rootNode = this.getRootNodeFrom(nodes);

        int result = solution.goodNodes(rootNode);

        assertEquals(4, result);
    }

    @Test
    public void test2() {
        Integer[] nodes = new Integer[]{3,3,null,4,2};

        final Solution solution = new Solution();
        final TreeNode rootNode = this.getRootNodeFrom(nodes);

        int result = solution.goodNodes(rootNode);

        assertEquals(3, result);
    }

    @Test
    public void test3() {
        Integer[] nodes = new Integer[]{1};

        final Solution solution = new Solution();
        final TreeNode rootNode = this.getRootNodeFrom(nodes);

        int result = solution.goodNodes(rootNode);

        assertEquals(1, result);
    }

    @Test
    public void test4() {
        Integer[] nodes = new Integer[]{2,null,4,10,8,null,null,4};

        final Solution solution = new Solution();
        final TreeNode rootNode = this.getRootNodeFrom(nodes);

        int result = solution.goodNodes(rootNode);

        assertEquals(4, result);
    }

    private TreeNode getRootNodeFrom(Integer[] nodes) {
        TreeNode rootNode = null;

        if (nodes.length > 0 && nodes[0] != null) {
            rootNode = new TreeNode(nodes[0]);

            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(rootNode);

            int currentLeftIndex = 1;

            while (!queue.isEmpty()) {
                final TreeNode parent = queue.poll();
                final int currentRightIndex = currentLeftIndex + 1;

                if (currentLeftIndex < nodes.length && nodes[currentLeftIndex] != null) {
                    TreeNode leftChild = new TreeNode(nodes[currentLeftIndex]);
                    parent.left = leftChild;

                    queue.add(leftChild);
                }

                if (currentRightIndex < nodes.length && nodes[currentRightIndex] != null) {
                    TreeNode rightChild = new TreeNode(nodes[currentRightIndex]);
                    parent.right = rightChild;

                    queue.add(rightChild);
                }

                currentLeftIndex += 2;
            }
        }

        return rootNode;
    }
}
