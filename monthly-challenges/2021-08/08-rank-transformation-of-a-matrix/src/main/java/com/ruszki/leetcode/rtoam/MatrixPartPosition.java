package com.ruszki.leetcode.rtoam;

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
