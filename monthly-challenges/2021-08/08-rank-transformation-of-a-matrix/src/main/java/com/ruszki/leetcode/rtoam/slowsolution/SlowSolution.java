package com.ruszki.leetcode.rtoam.slowsolution;

import java.util.*;

public class SlowSolution {
    public int[][] matrixRankTransform(int[][] matrix) {
        MatrixElement[][] rankElementMatrix = createRankElementMatrix(matrix);

        List<MatrixElement> smallestValueElements = sortMatrixElementsAndGetSmallest(rankElementMatrix);

        return createRankMatrix(matrix, smallestValueElements);
    }

    private MatrixElement[][] createRankElementMatrix(int[][] matrix) {
        MatrixElement[][] rankElementMatrix = new MatrixElement[matrix.length][];

        for (int rowIndex = 0; rowIndex < rankElementMatrix.length; rowIndex++) {
            rankElementMatrix[rowIndex] = new MatrixElement[matrix[rowIndex].length];

            for (int columnIndex = 0; columnIndex < rankElementMatrix[rowIndex].length; columnIndex++) {
                int value = matrix[rowIndex][columnIndex];

                // Check if there is an element with the same value in the row and store it
                MatrixElement sameValueRowElement = null;

                for (int sameValueColumnIndex = 0; sameValueColumnIndex < columnIndex; sameValueColumnIndex++) {
                    if (value == rankElementMatrix[rowIndex][sameValueColumnIndex].getValue()) {
                        sameValueRowElement = rankElementMatrix[rowIndex][sameValueColumnIndex];

                        break;
                    }
                }

                // Elements with the same value in a row should have the same element object
                if (sameValueRowElement != null) {
                    rankElementMatrix[rowIndex][columnIndex] = sameValueRowElement;

                    // Individual positions are stored also in the element itself
                    sameValueRowElement.addPosition(rowIndex, columnIndex);
                }

                // Check if there is an element with the same value in the column and store it
                MatrixElement sameValueColumnElement = null;

                for (int sameValueRowIndex = 0; sameValueRowIndex < rowIndex; sameValueRowIndex++) {
                    if (value == rankElementMatrix[sameValueRowIndex][columnIndex].getValue()) {
                        sameValueColumnElement = rankElementMatrix[sameValueRowIndex][columnIndex];

                        break;
                    }
                }

                // Elements with the same value in a column should have the same element object
                if (sameValueColumnElement != null) {
                    if (sameValueRowElement != null) {
                        // Merge elements with the same value in the given column and row if they aren't the same
                        // already
                        if (sameValueColumnElement != sameValueRowElement) {
                            // Replace the element object in the column with the one used previously for the row
                            for (MatrixPosition position : sameValueColumnElement.getPositions()) {
                                sameValueRowElement.addPosition(position.getRow(), position.getColumn());
                                rankElementMatrix[position.getRow()][position.getColumn()] = sameValueRowElement;
                            }
                        }
                    } else {
                        rankElementMatrix[rowIndex][columnIndex] = sameValueColumnElement;
                        sameValueColumnElement.addPosition(rowIndex, columnIndex);
                    }

                    continue;
                } else if (sameValueRowElement != null) {
                    continue;
                }

                // If no elements found with the same value in the row or in the column then create a new element object
                rankElementMatrix[rowIndex][columnIndex] =
                        new MatrixElement(matrix[rowIndex][columnIndex], rowIndex, columnIndex);
            }
        }

        return rankElementMatrix;
    }

    private List<MatrixElement> sortMatrixElementsAndGetSmallest(MatrixElement[][] rankElementMatrix) {
        Set<MatrixElement> smallestValueRowElements = new HashSet<>();

        // Sort elements in rows, link them in ascending order, and store the smallest elements
        for (MatrixElement[] rankMatrixRow : rankElementMatrix) {
            // Sort elements by TreeSets
            final Set<MatrixElement> sortedElementRow = new TreeSet<>(Arrays.asList(rankMatrixRow));

            MatrixElement previousElement = null;
            for (MatrixElement sortedElement : sortedElementRow) {
                if (previousElement != null) {
                    // Link items in ascending order in a row
                    previousElement.addNextRowElement(sortedElement);
                } else {
                    // If the element is the smallest in its row then store it
                    smallestValueRowElements.add(sortedElement);
                }

                previousElement = sortedElement;
            }
        }

        List<MatrixElement> smallestValueElements = new ArrayList<>();

        // Sort elements in columns, link them in ascending order, and store the smallest elements for every row and
        // column
        if (rankElementMatrix.length > 0) {
            for (int columnIndex = 0; columnIndex < rankElementMatrix[0].length; columnIndex++) {
                // Sort elements by TreeSets
                final Set<MatrixElement> sortedElementColumn = new TreeSet<>();

                for (MatrixElement[] matrixElements : rankElementMatrix) {
                    MatrixElement element = matrixElements[columnIndex];
                    sortedElementColumn.add(element);
                }

                MatrixElement previousElement = null;
                for (MatrixElement sortedElement : sortedElementColumn) {
                    if (previousElement != null) {
                        // Link items in ascending order in a column
                        previousElement.addNextColumnElement(sortedElement);
                    } else {
                        // If the element is the smallest in the given row and column then store it
                        if (smallestValueRowElements.contains(sortedElement)) {
                            smallestValueElements.add(sortedElement);
                        }
                    }

                    previousElement = sortedElement;
                }
            }
        }

        return smallestValueElements;
    }

    private int[][] createRankMatrix(int[][] matrix, List<MatrixElement> smallestValueElements) {
        int[][] rankMatrix = new int[matrix.length][];

        for (int rankMatrixRowIndex = 0; rankMatrixRowIndex < matrix.length; rankMatrixRowIndex++) {
            rankMatrix[rankMatrixRowIndex] = new int[matrix[rankMatrixRowIndex].length];
        }

        int rank = 1;
        List<MatrixElement> processElements = smallestValueElements;

        while (!processElements.isEmpty()) {
            final Set<MatrixElement> newProcessElements = new HashSet<>();

            for (MatrixElement element : processElements) {
                for (MatrixPosition position : element.getPositions()) {
                    rankMatrix[position.getRow()][position.getColumn()] = rank;
                }

                newProcessElements.addAll(element.getNextRowElements());
                newProcessElements.addAll(element.getNextColumnElements());
            }

            processElements = List.copyOf(newProcessElements);
            rank += 1;
        }

        return rankMatrix;
    }
}