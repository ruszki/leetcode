package com.ruszki.leetcode.rtoam;

// Represents an element in the disjoint set
public class DisjointSetElement<T extends Comparable<T>> implements Comparable<DisjointSetElement<T>> {
    // The link to the parent element
    private DisjointSetElement<T> parent;
    // The rank. Only ranks of root elements represents their tree's rank properly. Child node's ranks can be wrong.
    private int rank;
    // The value of the element
    private final T value;

    public DisjointSetElement(T value) {
        this.parent = this;
        this.rank = 0;
        this.value = value;
    }

    public void setParent(DisjointSetElement<T> parent) {
        this.parent = parent;
    }

    public void increaseRank() {
        this.rank += 1;
    }

    public DisjointSetElement<T> getParent() {
        return this.parent;
    }

    public int getRank() {
        return this.rank;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public int compareTo(DisjointSetElement<T> o) {
        return this.value.compareTo(o.value);
    }
}
