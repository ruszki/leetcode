package com.ruszki.leetcode.rtoam;

// Stores a position in a matrix by row and column numbers
public class MatrixPosition {
    private final MatrixRowPosition row;
    private final MatrixColumnPosition column;

    public MatrixPosition(int row, int column) {
        this.row = new MatrixRowPosition(row);
        this.column = new MatrixColumnPosition(column);
    }

    public MatrixRowPosition getRow() {
        return this.row;
    }

    public MatrixColumnPosition getColumn() {
        return this.column;
    }
}
