package com.ruszki.leetcode.fstmi;

class Solution {
    // The algorithm goes through every place where the change from zeros to ones can be. It calculates how many
    // zeros should be changed to be only ones in the string. This considered the worst case scenario, when the best
    // transformation is to change every zeros to ones. It goes through all possible change points from
    // zero to one. If the current character is zero, then from the previous change point it reduce the necessary
    // changes by one. If it's one, then it increases. It stores the lowest difference from the worst case scenario.
    // The difference doesn't need to consider characters after the currently investigated change point because the
    // necessary change count is the same for every previous change points, and in the worst case scenario they will
    // be included, so only the difference from the worst case scenario in case of previous characters need to be
    // considered.
    public int minFlipsMonoIncr(String s) {
        // The number of zeros in the string. This is the worst case scenario transformation
        int zeros = 0;
        // The lowest found difference from the worst case scenario
        int minimumDiff = 0;
        // The current difference from the worst case scenario
        int currentDiff = 0;

        for (int i = 0; i < s.length(); i++) {
            // If the character is a zero, then the difference from the worst case scenario is reduced by one if the
            // change point is after this character
            if (s.charAt(i) == '0') {
                zeros += 1;

                currentDiff -= 1;
            } else {
                // If it's a one then it increases the difference
                currentDiff += 1;
            }

            // Check if a new minimum difference is found
            if (minimumDiff > currentDiff) {
                minimumDiff = currentDiff;
            }
        }

        // Add the considered worst case scenario to the minimum difference found.
        return zeros + minimumDiff;
    }
}
