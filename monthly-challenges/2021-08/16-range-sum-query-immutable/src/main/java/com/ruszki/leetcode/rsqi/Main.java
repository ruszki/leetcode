package com.ruszki.leetcode.rsqi;

public class Main {
    public static void main(String[] args) {
        final int[] arr = new int[]{-2,0,3,-5,2,-1};

        final NumArray solution = new NumArray(arr);
        System.out.println(solution.sumRange(0, 2));
        System.out.println(solution.sumRange(2, 5));
        System.out.println(solution.sumRange(0, 5));
    }
}
