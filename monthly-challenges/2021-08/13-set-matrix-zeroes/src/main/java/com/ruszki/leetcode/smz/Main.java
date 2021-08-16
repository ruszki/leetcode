package com.ruszki.leetcode.smz;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{new int[]{1,1,1},new int[]{1,0,1},new int[]{1,1,1}};

        int[][] matrix2 = new int[][]{new int[]{0,1,2,0},new int[]{3,4,5,2},new int[]{1,3,1,5}};

        final Solution solution = new Solution();
        solution.setZeroes(matrix2);
        System.out.println(Arrays.deepToString(matrix2));
    }
}
