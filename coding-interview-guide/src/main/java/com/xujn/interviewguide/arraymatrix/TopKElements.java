package com.xujn.interviewguide.arraymatrix;

import java.util.PriorityQueue;

/**
 * 求数组中最大的 TopK 元素。
 */
public class TopKElements {

    public int[] topK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return new int[0];
        }
        k = Math.min(k, arr.length);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int value : arr) {
            if (minHeap.size() < k) {
                minHeap.offer(value);
            } else if (value > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(value);
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}
