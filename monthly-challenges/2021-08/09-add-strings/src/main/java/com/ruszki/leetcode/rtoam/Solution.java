package com.ruszki.leetcode.rtoam;

class Solution {
    // Add two numbers represented by strings and return result in a string
    public String addStrings(String num1, String num2) {
        // Sort them by size
        final String shorterNum = num1.length() >= num2.length() ? num2 : num1;
        final String longerNum = num1.length() >= num2.length() ? num1 : num2;

        // The sum will be created in a revert fashion
        StringBuilder revertedSum = new StringBuilder();
        // Store remainder after adding two single number
        char remainder = 0;

        for (int i = 0; i < longerNum.length(); i++) {
            // Store the current characters shifted by '0' character
            char currentLongerNum = (char) (longerNum.charAt(longerNum.length() - i - 1) - '0');
            char currentShorterNum = 0;

            if (shorterNum.length() > i) {
                currentShorterNum = (char) (shorterNum.charAt(shorterNum.length() - i - 1) - '0');
            }

            char currentSumNum = (char) (currentLongerNum + currentShorterNum);

            // If there was a remainder from previous step then add it
            if (remainder > 0) {
                currentSumNum += remainder;
            }

            // Handling remainder for the next step
            if (currentSumNum > 9) {
                // The sum for the current characters are definitely less than 19, so the remainder can only be 1
                remainder = 1;
                currentSumNum -= 10;
            } else {
                // Reset remainder if the sum was less than 10
                remainder = 0;
            }

            // Append the result to the sum shifted back by '0'
            revertedSum.append((char) (currentSumNum + '0'));
        }

        // If there was any remainder left, then add it
        if (remainder > 0) {
            revertedSum.append((char) (remainder + '0'));
        }

        // Revert the sum to its proper form and return it
        return revertedSum.reverse().toString();
    }
}
