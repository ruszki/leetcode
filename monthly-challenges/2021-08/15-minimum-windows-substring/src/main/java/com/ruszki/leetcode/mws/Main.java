package com.ruszki.leetcode.mws;

public class Main {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        final Solution solution = new Solution();
        System.out.println(solution.minWindow(s, t));
    }
}
