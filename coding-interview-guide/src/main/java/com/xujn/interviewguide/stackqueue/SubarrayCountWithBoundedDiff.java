package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大值减最小值小于等于 num 的子数组数量。
 */
public class SubarrayCountWithBoundedDiff {

    public int count(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int right = 0;
        int result = 0;
        for (int left = 0; left < arr.length; left++) {
            while (right < arr.length) {
                while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] <= arr[right]) {
                    maxDeque.pollLast();
                }
                maxDeque.offerLast(right);
                while (!minDeque.isEmpty() && arr[minDeque.peekLast()] >= arr[right]) {
                    minDeque.pollLast();
                }
                minDeque.offerLast(right);
                if (arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()] > num) {
                    if (maxDeque.peekLast() == right) {
                        maxDeque.pollLast();
                    }
                    if (minDeque.peekLast() == right) {
                        minDeque.pollLast();
                    }
                    break;
                }
                right++;
            }
            result += right - left;
            if (!maxDeque.isEmpty() && maxDeque.peekFirst() == left) {
                maxDeque.pollFirst();
            }
            if (!minDeque.isEmpty() && minDeque.peekFirst() == left) {
                minDeque.pollFirst();
            }
        }
        return result;
    }
}
