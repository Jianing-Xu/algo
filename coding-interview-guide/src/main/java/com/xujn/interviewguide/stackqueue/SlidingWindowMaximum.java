package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 生成窗口最大值数组。
 */
public class SlidingWindowMaximum {

    public int[] getMaxWindow(int[] arr, int windowSize) {
        if (arr == null || windowSize < 1 || arr.length < windowSize) {
            return new int[0];
        }
        int[] result = new int[arr.length - windowSize + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (deque.peekFirst() == i - windowSize) {
                deque.pollFirst();
            }
            if (i >= windowSize - 1) {
                result[i - windowSize + 1] = arr[deque.peekFirst()];
            }
        }
        return result;
    }
}
