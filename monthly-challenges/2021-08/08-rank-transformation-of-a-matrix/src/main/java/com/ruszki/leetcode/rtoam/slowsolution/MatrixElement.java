package com.ruszki.leetcode.rtoam.slowsolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// An element in the matrix which has an individual rank
public class MatrixElement implements Comparable<MatrixElement> {
    // The value of the element
    private final int value;
    // Positions in the matrix where this element can be found
    private final List<MatrixPosition> positions;
    // Next elements in ascending order in the rows of every position
    private final Set<MatrixElement> nextRowElements;
    // Next elements in ascending order in the columns of every position
    private final Set<MatrixElement> nextColumnElements;

    public MatrixElement(int value, int row, int column) {
        this.value = value;
        this.positions = new ArrayList<>();
        this.nextRowElements = new HashSet<>();
        this.nextColumnElements = new HashSet<>();

        this.positions.add(new MatrixPosition(row, column));
    }

    public void addPosition(int row, int column) {
        this.positions.add(new MatrixPosition(row, column));
    }

    public void addNextRowElement(MatrixElement nextRowElement) {
        this.nextRowElements.add(nextRowElement);
    }

    public void addNextColumnElement(MatrixElement nextColumnElement) {
        this.nextColumnElements.add(nextColumnElement);
    }

    public int getValue() {
        return this.value;
    }

    public List<MatrixPosition> getPositions() {
        return this.positions;
    }

    public List<MatrixElement> getNextRowElements() {
        return List.copyOf(this.nextRowElements);
    }

    public List<MatrixElement> getNextColumnElements() {
        return List.copyOf(this.nextColumnElements);
    }

    // Compare by value for usage in TreeSets
    @Override
    public int compareTo(MatrixElement o) {
        if (o == null) {
            throw new NullPointerException();
        }

        return Integer.compare(this.value, o.value);
    }
}