package com.ruszki.leetcode.rb;

class Solution {
    public int removeBoxes(int[] boxes) {
        int[][][] memo = new int[boxes.length][boxes.length][boxes.length];

        return removeBoxes(boxes, 0, boxes.length - 1, 0, memo);
    }

    private int removeBoxes(int[] boxes, int startIndex, int endIndex, int leftSameBoxes, int[][][] memo) {
        if (startIndex > endIndex) {
            return 0;
        }

        if (memo[startIndex][endIndex][leftSameBoxes] > 0) {
            return memo[startIndex][endIndex][leftSameBoxes];
        }

        int newStartIndex = startIndex;
        int newLeftSameBoxes = leftSameBoxes;

        while (newStartIndex < endIndex && boxes[newStartIndex + 1] == boxes[startIndex]) {
            newStartIndex += 1;
            newLeftSameBoxes += 1;
        }

        memo[startIndex][endIndex][leftSameBoxes] = removeBoxes(boxes, newStartIndex + 1, endIndex, 0, memo) + (int) Math.pow(newLeftSameBoxes + 1, 2);

        for (int sameBoxIndex = newStartIndex + 1; sameBoxIndex <= endIndex; sameBoxIndex++) {
            if (boxes[sameBoxIndex] == boxes[startIndex]) {
                memo[startIndex][endIndex][leftSameBoxes] =
                        Math.max(memo[startIndex][endIndex][leftSameBoxes],
                                removeBoxes(boxes, newStartIndex + 1, sameBoxIndex - 1, 0, memo) +
                                removeBoxes(boxes, sameBoxIndex, endIndex, newLeftSameBoxes + 1, memo));
            }
        }

        return memo[startIndex][endIndex][leftSameBoxes];
    }
}
