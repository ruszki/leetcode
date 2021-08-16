package com.ruszki.leetcode.mws;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, LinkedList<Integer>> tElementsInWindow = new HashMap<>();
        LinkedList<Integer> tElementsInS = new LinkedList<>();
        Map<Character, Integer> tElementCount = new HashMap<>();
        int missingCount = t.length();
        int minimumWindowSizeStart = -1;
        int minimumWindowSizeEnd = -1;

        for (char c : t.toCharArray()) {
            if (tElementCount.containsKey(c)) {
                tElementCount.put(c, tElementCount.get(c) + 1);
            } else {
                tElementCount.put(c, 1);
                tElementsInWindow.put(c, new LinkedList<>());
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (tElementCount.containsKey(c)) {
                tElementsInWindow.get(c).add(i);
                tElementsInS.add(i);

                if (tElementsInS.size() == 1) {
                    missingCount -= 1;
                } else {
                    if (tElementsInWindow.get(c).size() <= tElementCount.get(c)) {
                        missingCount -= 1;
                    } else {
                        if (Objects.equals(tElementsInWindow.get(c).peek(), tElementsInS.peek())) {
                            tElementsInWindow.get(c).poll();
                            tElementsInS.poll();

                            Integer removalProposalIndex = tElementsInS.peek();
                            Character removalProposal = removalProposalIndex != null ? s.charAt(removalProposalIndex)
                                    : null;

                            while (removalProposal != null
                                    && tElementsInWindow.get(removalProposal).size()
                                    > tElementCount.get(removalProposal)) {
                                tElementsInWindow.get(removalProposal).poll();
                                tElementsInS.poll();

                                removalProposalIndex = tElementsInS.peek();
                                removalProposal = removalProposalIndex != null ? s.charAt(removalProposalIndex)
                                        : null;
                            }
                        }
                    }
                }

                if (missingCount == 0 && tElementsInS.size() > 0) {
                    final int minimumWindowSize = minimumWindowSizeEnd - minimumWindowSizeStart;

                    if (minimumWindowSizeStart < 0
                            || (tElementsInS.peekLast() - tElementsInS.peek() < minimumWindowSize)) {
                        minimumWindowSizeStart = tElementsInS.peek();
                        minimumWindowSizeEnd = tElementsInS.peekLast();
                    }
                }
            }
        }

        if (minimumWindowSizeStart < 0) {
            return "";
        } else {
            return s.substring(minimumWindowSizeStart, minimumWindowSizeEnd + 1);
        }
    }
}
