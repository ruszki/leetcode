package com.ruszki.leetcode.rtoam;

// Represents either a row or a column in a matrix
public class MatrixPartPosition implements Comparable<MatrixPartPosition> {
    private final MatrixPartPositionType type;
    private final int value;

    protected MatrixPartPosition(MatrixPartPositionType type, int value) {
        this.type = type;
        this.value = value;
    }

    public MatrixPartPositionType getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isRow() {
        return this.type == MatrixPartPositionType.ROW;
    }

    public boolean isColumn() {
        return this.type == MatrixPartPositionType.COLUMN;
    }

    // Rows are considered smaller than columns. This is needed to be able to use with the disjoint set, but it can be
    // also the other way around.
    @Override
    public int compareTo(MatrixPartPosition o) {
        if (o == null) {
            throw new NullPointerException();
        } else if (this == o) {
            return 0;
        } else if (this.type == MatrixPartPositionType.ROW && o.type == MatrixPartPositionType.COLUMN) {
            return -1;
        } else if (this.type == MatrixPartPositionType.COLUMN && o.type == MatrixPartPositionType.ROW) {
            return 1;
        } else {
            return Integer.compare(this.value, o.value);
        }
    }

    public enum MatrixPartPositionType {
        ROW, COLUMN
    }
}
