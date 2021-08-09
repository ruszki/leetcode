package com.ruszki.leetcode.rtoam;

import java.util.*;

public class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        // Gather all unique value and their positions where they can be found in ascending order
        final TreeMap<Integer, List<MatrixPosition>> positionsByValues = createPositionsByValues(matrix);
        // The matrix which will include ranks of all elements in the original matrix
        final int[][] rankMatrix = createEmptyRankMatrix(matrix);

        updateRankListAndRankMatrix(positionsByValues, matrix, rankMatrix);

        return rankMatrix;
    }

    // Gather all values in asceding order in the matrix and store their positions
    private TreeMap<Integer, List<MatrixPosition>> createPositionsByValues(int[][] matrix) {
        final TreeMap<Integer, List<MatrixPosition>> positionsByValues = new TreeMap<>();

        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrix[rowIndex].length; columnIndex++) {
                int value = matrix[rowIndex][columnIndex];
                List<MatrixPosition> positionsByValue =
                        positionsByValues.computeIfAbsent(value, k -> new ArrayList<>());

                positionsByValue.add(new MatrixPosition(rowIndex, columnIndex));
            }
        }

        return positionsByValues;
    }

    // Create a matrix for ranks according to the input matrix. It will have the same size, and all of its elements will
    // be zeros by default.
    private int[][] createEmptyRankMatrix(int[][] matrix) {
        final int[][] rankMatrix = new int[matrix.length][];

        for (int rankMatrixRowIndex = 0; rankMatrixRowIndex < matrix.length; rankMatrixRowIndex++) {
            rankMatrix[rankMatrixRowIndex] = new int[matrix[rankMatrixRowIndex].length];
        }

        return rankMatrix;
    }

    // Go through all unique values in ascending order to update the matrix of ranks
    private void updateRankListAndRankMatrix(TreeMap<Integer, List<MatrixPosition>> positionsByValues, int[][] matrix,
                                             int[][] rankMatrix) {
        // A list which contains the current rank for every row and column
        final RankList rankList = new RankList(matrix);

        for (final List<MatrixPosition> positions : positionsByValues.values()) {
            // A temporary list of ranks which will be used to increase ranks properly
            final RankList tempRankList = new RankList(rankList);
            // A disjoint set to connect ranks of rows and columns in the case of same values
            final DisjointSet<MatrixPartPosition> set = createNewDisjointSet(matrix);

            connectRowsAndColumns(positions, set, tempRankList);
            updateRanks(positions, set, tempRankList, rankList, rankMatrix);
        }
    }

    // Create a disjoint set and insert columns and rows into it
    private DisjointSet<MatrixPartPosition> createNewDisjointSet(int[][] matrix) {
        final DisjointSet<MatrixPartPosition> set = new DisjointSet<>();

        for (int i = 0; i < matrix.length + matrix[0].length; i++) {
            set.insert(new MatrixRowPosition(i));
        }
        for (int i = 0; i < matrix[0].length; i++) {
            set.insert(new MatrixColumnPosition(i));
        }

        return set;
    }

    // Connect rows and columns in the disjoint set for elements of the same value
    private void connectRowsAndColumns(List<MatrixPosition> positions, DisjointSet<MatrixPartPosition> set,
                                       RankList tempRankList) {
        for (final MatrixPosition position : positions) {
            final MatrixRowPosition x = position.getRow();
            final MatrixColumnPosition y = position.getColumn();

            // Connect the given row and column by a union procedure on the disjoint set
            final DisjointSet<MatrixPartPosition>.UnionRoots unionRoots = set.union(x, y);

            // If two trees were merged in the disjoint set then update the rank of the new root accordingly
            if (unionRoots.getNewRoot() != unionRoots.getOldRoot()) {
                final int oldNewRootRank = tempRankList.getRank(unionRoots.getNewRoot());
                final int oldOldRootRank = tempRankList.getRank(unionRoots.getOldRoot());

                // The new rank of the new common root will be the maximum of the ranks of the two roots previously
                tempRankList.setRank(unionRoots.getNewRoot(), Math.max(oldNewRootRank, oldOldRootRank));
            }
        }
    }

    // Update ranks according to the new ranks coming from unions in the disjoint tree
    private void updateRanks(List<MatrixPosition> positions, DisjointSet<MatrixPartPosition> set,
                             RankList tempRankList, RankList rankList, int[][] rankMatrix) {
        for (final MatrixPosition position : positions) {
            // The rank of the root will be used
            final MatrixPartPosition rootPartPosition = set.findRoot(position.getRow());

            // The rank of the root is the largest rank which was used before, it should be increased for the new
            // element
            final int newRank = tempRankList.getRank(rootPartPosition) + 1;

            // Set rank for the row and also for the column
            rankList.setRank(position.getRow(), newRank);
            rankList.setRank(position.getColumn(), newRank);

            // Update the rank matrix with the proper rank for the given position
            rankMatrix[position.getRow().getValue()][position.getColumn().getValue()] = newRank;
        }
    }
}
