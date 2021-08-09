package com.ruszki.leetcode.rtoam;

import java.util.Arrays;

// A list of ranks at a given time for every row and columns
public class RankList {
    private final int[] rowRankList;
    private final int[] columnRankList;

    // Create a rank list from a matrix. Every rank will be zero by default.
    public RankList(int[][] matrix) {
        this.rowRankList = new int[matrix.length];
        this.columnRankList = new int[matrix.length > 0 ? matrix[0].length : 0];
    }

    // Create a copy from another rank list
    public RankList(RankList oldRankList) {
        this.rowRankList = Arrays.copyOf(oldRankList.rowRankList, oldRankList.rowRankList.length);
        this.columnRankList = Arrays.copyOf(oldRankList.columnRankList, oldRankList.columnRankList.length);
    }

    // Get the rank of the given row or column
    public int getRank(MatrixPartPosition position) {
        if (position.isRow()) {
            return this.rowRankList[position.getValue()];
        } else if (position.isColumn()) {
            return this.columnRankList[position.getValue()];
        } else {
            throw new RuntimeException("MatrixPartPosition type " + position.getType().name() + " is not supported");
        }
    }

    // Set the rank for the given row or column
    public void setRank(MatrixPartPosition position, int rank) {
        if (position.isRow()) {
            this.rowRankList[position.getValue()] = rank;
        } else if (position.isColumn()) {
            this.columnRankList[position.getValue()] = rank;
        } else {
            throw new RuntimeException("MatrixPartPosition type " + position.getType().name() + " is not supported");
        }
    }
}
