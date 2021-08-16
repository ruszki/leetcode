package com.ruszki.leetcode.rsqi;

class NumArray {
    final int[] sumNums;

    public NumArray(int[] nums) {
        this.sumNums = new int[nums.length + 1];
        this.sumNums[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            this.sumNums[i + 1] = this.sumNums[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return this.sumNums[right + 1] - this.sumNums[left];
    }
}
