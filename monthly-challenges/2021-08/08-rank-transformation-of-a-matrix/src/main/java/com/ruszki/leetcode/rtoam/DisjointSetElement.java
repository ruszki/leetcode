package com.ruszki.leetcode.rtoam;

public class DisjointSetElement<T extends Comparable<T>> implements Comparable<DisjointSetElement<T>> {
    private DisjointSetElement<T> parent;
    private int rank;
    private T value;

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
