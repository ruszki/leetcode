package com.ruszki.leetcode.rtoam;

import java.util.Map;
import java.util.TreeMap;

// Represents a disjoint set (https://en.wikipedia.org/wiki/Disjoint-set_data_structure)
public class DisjointSet<T extends Comparable<T>> {
    // Internally it's represented by a map. The forest is implemented by connecting its elements into trees.
    private final Map<T, DisjointSetElement<T>> forest;

    public DisjointSet() {
        this.forest = new TreeMap<>();
    }

    // Insert a value if it's not in the set already
    public void insert(T value) {
        if (!this.forest.containsKey(value)) {
            this.forest.put(value, new DisjointSetElement<>(value));
        }
    }

    // Find the value of the root of a given value
    public T findRoot(T value) {
        return this.findRootElement(value).getValue();
    }

    // Merge two trees into one. The new root will be the one which has the larger rank.
    public UnionRoots union(T xValue, T yValue) {
        if (this.forest.containsKey(xValue) && this.forest.containsKey(yValue)) {
            // Find root elements for both values
            final DisjointSetElement<T> xRoot = this.findRootElement(xValue);
            final DisjointSetElement<T> yRoot = this.findRootElement(yValue);

            // Do nothing if both elements are already in the same tree
            if (xRoot != yRoot) {
                // Specify which tree has the larger rank
                final DisjointSetElement<T> smallerRoot = xRoot.getRank() < yRoot.getRank() ? xRoot : yRoot;
                final DisjointSetElement<T> largerRoot = xRoot.getRank() < yRoot.getRank() ? yRoot : xRoot;

                // Merge the tree with the smaller rank into the one which larger rank.
                // In case both have the same rank the tree represented by the second value will be merged into the
                // other one.
                smallerRoot.setParent(largerRoot);

                // Increase rank of the merged tree if the two original tree had the same rank
                if (largerRoot.getRank() == smallerRoot.getRank()) {
                    largerRoot.increaseRank();
                }

                // Return the new root, and the root merged into the new one
                return new UnionRoots(largerRoot.getValue(), smallerRoot.getValue());
            } else {
                return new UnionRoots(xRoot.getValue(), yRoot.getValue());
            }
        } else {
            throw new NullPointerException();
        }
    }

    // Find the root of a given value
    public DisjointSetElement<T> findRootElement(T value) {
        if (this.forest.containsKey(value)) {
            DisjointSetElement<T> element = this.forest.get(value);

            // Traverse the tree by moving up in it with the help of parent links
            // Stop when a root is reached represented by the element's parent being the element itself
            while (element != element.getParent()) {
                // During movement, update parent links to improve performance for future root element search
                DisjointSetElement<T> parent = element.getParent();
                DisjointSetElement<T> grandParent = element.getParent().getParent();
                // Update parent to link to the grandparent
                element.setParent(grandParent);
                element = parent;
            }

            return element;
        } else {
            throw new NullPointerException();
        }
    }

    // Helper class to return the new root, and the one merged into the new root during union procedure
    public class UnionRoots {
        private final T newRoot;
        private final T oldRoot;

        public UnionRoots(T newRoot, T oldRoot) {
            this.newRoot = newRoot;
            this.oldRoot = oldRoot;
        }

        public T getNewRoot() {
            return this.newRoot;
        }

        public T getOldRoot() {
            return this.oldRoot;
        }
    }
}
