package com.ruszki.leetcode.rtoam;

import java.util.TreeMap;
import java.util.TreeSet;

public class DisjointSet<T extends Comparable<T>> {
    private final TreeMap<T, DisjointSetElement<T>> forest;

    public DisjointSet() {
        this.forest = new TreeMap<>();
    }

    public void insert(T value) {
        if (!this.forest.containsKey(value)) {
            this.forest.put(value, new DisjointSetElement<>(value));
        }
    }

    public T findRoot(T value) {
        return this.findRootElement(value).getValue();
    }

    public void union(T xValue, T yValue, UnionCallback<T> callback) {
        if (this.forest.containsKey(xValue) && this.forest.containsKey(yValue)) {
            DisjointSetElement<T> xRoot = this.findRootElement(xValue);
            DisjointSetElement<T> yRoot = this.findRootElement(yValue);

            if (xRoot == yRoot) {
                return;
            }

            if (xRoot.getRank() < yRoot.getRank()) {
                DisjointSetElement<T> tempRoot = xRoot;
                xRoot = yRoot;
                yRoot = tempRoot;
            }

            yRoot.setParent(xRoot);

            if (xRoot.getRank() == yRoot.getRank()) {
                xRoot.increaseRank();
            }

            if (callback != null) {
                callback.call(xRoot.getValue(), yRoot.getValue());
            }
        } else {
            throw new NullPointerException();
        }
    }

    public DisjointSetElement<T> findRootElement(T value) {
        if (this.forest.containsKey(value)) {
            DisjointSetElement<T> element = this.forest.get(value);

            while (element != element.getParent()) {
                DisjointSetElement<T> parent = element.getParent();
                DisjointSetElement<T> grandParent = element.getParent().getParent();
                element.setParent(grandParent);
                element = parent;
            }

            return element;
        } else {
            throw new NullPointerException();
        }
    }

    public interface UnionCallback<T extends Comparable<T>> {
        void call(T newRoot, T oldRoot);
    }
}
