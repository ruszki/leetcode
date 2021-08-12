package com.ruszki.leetcode.ga;

// Represents a string whose characters are ordered. It also stores the index of the original string in the source array
public class OrderedString implements Comparable<OrderedString> {
    private final String orderedStr;
    private final int originalIndex;

    public OrderedString(String orderedStr, int originalIndex) {
        this.orderedStr = orderedStr;
        this.originalIndex = originalIndex;
    }

    public int getOriginalIndex() {
        return originalIndex;
    }

    // For ordering only the internal string is used
    @Override
    public int compareTo(OrderedString o) {
        return this.orderedStr.compareTo(o.orderedStr);
    }

    // Comparison is done only on the internal string
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedString that = (OrderedString) o;
        return orderedStr.equals(that.orderedStr);
    }
}
