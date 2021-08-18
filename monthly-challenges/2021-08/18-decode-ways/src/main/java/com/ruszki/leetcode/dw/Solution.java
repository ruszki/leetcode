package com.ruszki.leetcode.dw;

class Solution {
    public int numDecodings(String s) {
        Character previousNumber = null;
        int previousVariationCount = 1;
        int variationCount = 1;

        for (Character c : s.toCharArray()) {
            int newVariationCount = 1;

            if (previousNumber != null) {
                if (previousNumber == '1' || (previousNumber == '2' && c <= '6')) {
                    if (c == '0') {
                        newVariationCount = previousVariationCount;
                    } else {
                        newVariationCount = previousVariationCount + variationCount;
                    }
                } else if (c == '0') {
                    return 0;
                } else {
                    newVariationCount = variationCount;
                }
            } else if (c == '0') {
                return 0;
            }

            previousVariationCount = variationCount;
            variationCount = newVariationCount;
            previousNumber = c;
        }

        return variationCount;
    }
}
