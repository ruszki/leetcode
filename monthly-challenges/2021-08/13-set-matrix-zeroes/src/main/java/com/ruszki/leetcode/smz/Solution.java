package com.ruszki.leetcode.smz;

import java.util.Arrays;

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean zeroFirstRow = isFirstRowZero(matrix);
        boolean zeroFirstColumn = isFirstColumnZero(matrix);

        setZeroFlags(matrix);

        makeRowsZeroes(matrix);
        makeColumnsZeroes(matrix);

        if (zeroFirstRow) {
            Arrays.fill(matrix[0], 0);
        }

        if (zeroFirstColumn) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }
    }

    private boolean isFirstRowZero(int[][] matrix) {
        if (matrix.length > 0) {
            for (int firstRowElement : matrix[0]) {
                if (firstRowElement == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isFirstColumnZero(int[][] matrix) {
        for (int[] rowElements : matrix) {
            if (rowElements[0] == 0) {
                return true;
            }
        }

        return false;
    }

    private void setZeroFlags(int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[row].length; column++) {
                if (matrix[row][column] == 0) {
                    matrix[0][column] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
    }

    private void makeRowsZeroes(int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int column = 1; column < matrix[row].length; column++) {
                    matrix[row][column] = 0;
                }
            }
        }
    }

    private void makeColumnsZeroes(int[][] matrix) {
        if (matrix.length > 0) {
            for (int column = 1; column < matrix[0].length; column++) {
                if (matrix[0][column] == 0) {
                    for (int row = 1; row < matrix.length; row++) {
                        matrix[row][column] = 0;
                    }
                }
            }
        }
    }
}
