package com.ruszki.leetcode.rtoam.slowsolution;

// Stores a position in a matrix by row and column numbers
public class MatrixPosition {
    private final int row;
    private final int column;

    public MatrixPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}
