package com.ruszki.leetcode.ppii;

import java.util.*;

// Class to search for the shortest palindrome chain in a string
// A palindrome chain is a list of consecutive palindromes in a string
class PalindromeChainSearch {
    // Start and end characters for all of the palindromes which are longer than 1 character
    private final Map<Integer, List<Integer>> palindromeBorders;
    // All characters which is reached by the algorithm and a palindrome starts from it
    private final Set<Integer> reachedChars;
    // The currently processed palindrome's end characters
    private List<Integer> currentEndChars;
    // The number of steps taken to found the shortest palindrome chain
    private int currentLevel;
    // Whether a shortest palindrome chain is found
    private boolean isFoundShortest;
    // The size of the string
    private int stringSize;

    public PalindromeChainSearch(String s) {
        // Find all borders of palindromes
        this.palindromeBorders = this.findPalindromeBorders(s);

        this.reachedChars = new HashSet<>();
        this.currentEndChars = new ArrayList<>();
        this.currentLevel = 0;
        this.isFoundShortest = false;
        this.stringSize = s.length();

        // Initialize variables by starting the search with the first character
        if (s.length() == 1) {
            this.isFoundShortest = true;
        } else if (s.length() > 1) {
            final List<Integer> palindromeEndChars = this.palindromeBorders.get(0);

            // Always add the character itself, because one single character is always a palindrome
            this.currentEndChars.add(0);

            if (palindromeEndChars != null) {
                for (Integer endChar : palindromeEndChars) {
                    this.currentEndChars.add(endChar);

                    // If a palindrome's last char is also the string's last char, then finish
                    if (endChar == s.length() - 1) {
                        this.isFoundShortest = true;

                        break;
                    }
                }
            }
        }
    }

    // Perform the search
    public int search() {
        // Move one level in our tree down until the end of the string is not reached
        while (!this.isFoundShortest) {
            this.step();
        }

        return this.currentLevel;
    }

    // On an abstract level, a tree is built about consecutive palindromes in the string. With every new palindrome
    // in the chain it's checked whether the end of the string was reached.
    private void step() {
        if (!this.isFoundShortest) {
            this.currentLevel += 1;

            List<Integer> newEndChars = new ArrayList<>();

            for (Integer previousEndChar : this.currentEndChars) {
                final int nextCharIndex = previousEndChar + 1;

                // Handle every character as a palindrome, and check whether it's the last character in the string
                if (nextCharIndex == this.stringSize - 1) {
                    this.isFoundShortest = true;
                    return;
                }

                // If the character was already reached previously then do not process again
                if (!reachedChars.contains(nextCharIndex)) {
                    final List<Integer> palindromeEnds = this.palindromeBorders.get(nextCharIndex);

                    if (palindromeEnds != null) {
                        for (Integer palindromeEndChar : palindromeEnds) {
                            newEndChars.add(palindromeEndChar);

                            // If a palindrome's last char is also the string's last char, then finish
                            if (palindromeEndChar == this.stringSize - 1) {
                                this.isFoundShortest = true;
                                return;
                            }
                        }
                    }

                    newEndChars.add(nextCharIndex);
                    reachedChars.add(nextCharIndex);
                }
            }

            currentEndChars = newEndChars;
        }
    }

    // Find all of the palindromes which are larger than 1 characters
    private Map<Integer, List<Integer>> findPalindromeBorders(String s) {
        final Map<Integer, List<Integer>> palindromeEndsForStartChars = new HashMap<>();

        // Go through all characters which can be either in the middle of a palindrome,
        // or the middle is between the given character and the next one
        for (int index = 0; index < s.length() - 1; index++) {
            this.findPalindromesInSubstring(s, index - 1, index + 1, palindromeEndsForStartChars);
            this.findPalindromesInSubstring(s, index, index + 1, palindromeEndsForStartChars);
        }

        return palindromeEndsForStartChars;
    }

    // Find all palindromes whose center is between the given start and end index
    private void findPalindromesInSubstring(String s, int startIndex, int endIndex, Map<Integer,
            List<Integer>> palindromeEndsForStartChars) {
        while (startIndex >= 0 && endIndex < s.length()) {
            if (s.charAt(startIndex) != s.charAt(endIndex)) {
                break;
            } else {
                List<Integer> palindromeEnds =
                        palindromeEndsForStartChars.computeIfAbsent(startIndex, k -> new ArrayList<>());

                palindromeEnds.add(endIndex);

                startIndex -= 1;
                endIndex += 1;
            }
        }
    }
}