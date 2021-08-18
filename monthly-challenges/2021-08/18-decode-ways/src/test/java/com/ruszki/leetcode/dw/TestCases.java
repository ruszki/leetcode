package com.ruszki.leetcode.dw;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCases {
    @Test
    public void test1() {
        String s = "12";

        final Solution solution = new Solution();
        int result = solution.numDecodings(s);

        assertEquals(2, result);
    }

    @Test
    public void test2() {
        String s = "226";

        final Solution solution = new Solution();
        int result = solution.numDecodings(s);

        assertEquals(3, result);
    }

    @Test
    public void test3() {
        String s = "0";

        final Solution solution = new Solution();
        int result = solution.numDecodings(s);

        assertEquals(0, result);
    }

    @Test
    public void test4() {
        String s = "06";

        final Solution solution = new Solution();
        int result = solution.numDecodings(s);

        assertEquals(0, result);
    }

    @Test
    public void test5() {
        String s = "10";

        final Solution solution = new Solution();
        int result = solution.numDecodings(s);

        assertEquals(1, result);
    }
}
