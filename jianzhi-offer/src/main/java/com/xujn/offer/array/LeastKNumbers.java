package com.xujn.offer.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小的 k 个数。
 */
public class LeastKNumbers {

    public List<Integer> getLeastNumbers(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return List.of();
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
