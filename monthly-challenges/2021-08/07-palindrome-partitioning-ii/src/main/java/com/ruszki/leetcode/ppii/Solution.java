package com.ruszki.leetcode.ppii;

class Solution {
    public int minCut(String s) {
        PalindromeChainSearch palindromeChainSearch = new PalindromeChainSearch(s);

        return palindromeChainSearch.search();
    }
}