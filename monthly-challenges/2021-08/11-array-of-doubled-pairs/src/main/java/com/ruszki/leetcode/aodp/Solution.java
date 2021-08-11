package com.ruszki.leetcode.aodp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // This solution first sort the array, then goes through on it in ascending order. The smallest element will be
    // the smallest member of a pair. The other member of the pair will be in case of negative number half of the
    // smaller member, or in case of natural number the double of it. The member of pairs which was not encountered
    // yet goes into a queue. If the next element of the array equals to the head of the queue then it's removed from
    // the queue, and the algorithm moves to the next element. If the element doesn't equal to the head of the queue,
    // then the element is definitely the smaller member of a pair, because smaller numbers in the array were already
    // processed at the time. So the larger member of the pair can be added safely to the queue to be able to search for
    // it. A queue can be used because the algorithm moves in ascending order, so the first element for which the other
    // member still needs to be found will have the smallest one.
    public boolean canReorderDoubled(int[] arr) {
        // Sort the array
        Arrays.sort(arr);

        // Use queue to follow elements for which the smaller member was already processed
        Queue<Integer> queue = new LinkedList<>();

        for (int i : arr) {
            Integer queueHead = queue.peek();

            // If there is no pair for which the higher member wasn't found yet, or when the smallest larger member of
            // pairs which is searched for are larger than the current element (ie we can be sure that the current
            // number is the smaller member of a pair)
            if (queueHead == null || queueHead > i) {
                // Negative numbers need to be divided by 2
                if (i < 0) {
                    // If the negative number cannot be divided by 2, but it should be the smaller member of a pair
                    if (i % 2 != 0) {
                        return false;
                    } else {
                        queue.add(i / 2);
                    }
                // Positive numbers need to be multiplied by 2
                } else {
                    queue.add(i * 2);
                }
            } else if (queueHead < i) {
                // If the smallest larger member of pairs which is search for are smaller than the current element, then
                // it's certain that it won't be in the array because only larger elements are left in the remaining
                // part of the array
                return false;
            } else {
                // If the current element equals to the larger pair which is the smallest one, it will be removed from
                // the queue and the algorithm moves to the next element
                queue.poll();
            }
        }

        // If the queue contains any element, that means that there were elements which cannot be part of a proper pair
        return queue.isEmpty();
    }
}
