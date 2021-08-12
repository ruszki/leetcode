package com.ruszki.leetcode.ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // First the strings' characters are sorted, then the ordered strings themselves. Then simple comparison is used on
    // adjacent ordered strings whether they have the same characters.
    public List<List<String>> groupAnagrams(String[] strs) {
        final OrderedString[] orderedStrings = new OrderedString[strs.length];

        // Sort the strings' characters in order, and also stores the index of the original string in the input array
        for (int i = 0; i < strs.length; i++) {
            final char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            final String orderedStr = new String(charArray);

            orderedStrings[i] = new OrderedString(orderedStr, i);
        }

        // Sort the ordered strings. After this if two strings should be in the same group, then they will be next to
        // each other.
        Arrays.sort(orderedStrings);

        List<List<String>> groupedAnagrams = new ArrayList<>();
        OrderedString prevStr = null;

        // Compare strings next to each other. If they equal then they meant in the same group, if not then the current
        // string needs to go in a new group. It's sure that no new strings should be in the previous groups, because
        // the array is ordered, and only strings should be in the same group which have exactly the same characters in
        // the same order.
        for (OrderedString orderedString : orderedStrings) {
            String str = strs[orderedString.getOriginalIndex()];

            // If new group should be created for the new string
            if (!orderedString.equals(prevStr)) {
                List<String> groupOfAnagram = new ArrayList<>(1);
                groupOfAnagram.add(str);

                groupedAnagrams.add(groupOfAnagram);
            // If the last group should be used to add the current string
            } else {
                groupedAnagrams.get(groupedAnagrams.size() - 1).add(str);
            }

            prevStr = orderedString;
        }

        return groupedAnagrams;
    }
}
