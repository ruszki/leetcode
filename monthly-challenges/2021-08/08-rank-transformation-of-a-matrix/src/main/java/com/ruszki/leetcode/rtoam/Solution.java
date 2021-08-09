package com.ruszki.leetcode.rtoam;

import java.util.*;

public class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        final Map<Integer, List<MatrixPosition>> positionsByValues = new TreeMap<>();

        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrix[rowIndex].length; columnIndex++) {
                int value = matrix[rowIndex][columnIndex];
                List<MatrixPosition> positionsByValue =
                        positionsByValues.computeIfAbsent(value, k -> new ArrayList<>());

                positionsByValue.add(new MatrixPosition(rowIndex, columnIndex));
            }
        }

        int[] rowRankList = new int[matrix.length];
        int[] columnRankList = new int[matrix.length > 0 ? matrix[0].length : 0];

        int[][] rankMatrix = new int[matrix.length][];

        for (int rankMatrixRowIndex = 0; rankMatrixRowIndex < matrix.length; rankMatrixRowIndex++) {
            rankMatrix[rankMatrixRowIndex] = new int[matrix[rankMatrixRowIndex].length];
        }

        for (List<MatrixPosition> positions : positionsByValues.values()) {
            int[] tempRowRankList = Arrays.copyOf(rowRankList, rowRankList.length);
            int[] tempColumnRankList = Arrays.copyOf(columnRankList, columnRankList.length);

            DisjointSet<MatrixPartPosition> set = new DisjointSet<>();
            for (int i = 0; i < matrix.length + matrix[0].length; i++) {
                set.insert(new MatrixRowPosition(i));
            }
            for (int i = 0; i < matrix[0].length; i++) {
                set.insert(new MatrixColumnPosition(i));
            }

            for (MatrixPosition position : positions) {
                MatrixRowPosition x = position.getRow();
                MatrixColumnPosition y = position.getColumn();

                set.union(x, y,
                        (newRoot, oldRoot) -> {
                            int oldOldRootRank = 0;
                            if (oldRoot.getType() == MatrixPartPosition.MatrixPartPositionType.ROW) {
                                oldOldRootRank = tempRowRankList[oldRoot.getValue()];
                            } else if (oldRoot.getType() == MatrixPartPosition.MatrixPartPositionType.COLUMN) {
                                oldOldRootRank = tempColumnRankList[oldRoot.getValue()];
                            }

                            if (newRoot.getType() == MatrixPartPosition.MatrixPartPositionType.ROW) {
                                int oldNewRootRank = tempRowRankList[newRoot.getValue()];

                                tempRowRankList[newRoot.getValue()] = Math.max(oldNewRootRank, oldOldRootRank);
                            } else if (newRoot.getType() == MatrixPartPosition.MatrixPartPositionType.COLUMN) {
                                int oldNewRootRank = tempColumnRankList[newRoot.getValue()];

                                tempColumnRankList[newRoot.getValue()] = Math.max(oldNewRootRank, oldOldRootRank);
                            }
                        });
            }

            for (MatrixPosition position : positions) {
                MatrixPartPosition rootPartPosition = set.findRoot(position.getRow());

                int newRank = 1;

                if (rootPartPosition.getType() == MatrixPartPosition.MatrixPartPositionType.ROW) {
                    newRank = tempRowRankList[rootPartPosition.getValue()] + 1;
                } else if (rootPartPosition.getType() == MatrixPartPosition.MatrixPartPositionType.COLUMN) {
                    newRank = tempColumnRankList[rootPartPosition.getValue()] + 1;
                }

                rowRankList[position.getRow().getValue()] = newRank;
                columnRankList[position.getColumn().getValue()] = newRank;

                rankMatrix[position.getRow().getValue()][position.getColumn().getValue()] = newRank;
            }
        }

        return rankMatrix;
    }
}
