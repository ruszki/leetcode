package com.ruszki.leetcode.ppii;

import java.util.*;

class Solution {
    public int minCut(String s) {
        PalindromeChainSearch palindromeChainSearch = new PalindromeChainSearch(s);

        return palindromeChainSearch.search();
    }
}