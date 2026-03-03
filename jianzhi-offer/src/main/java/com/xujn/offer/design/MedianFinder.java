package com.xujn.offer.design;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数。
 */
public class MedianFinder {

    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upper = new PriorityQueue<>();

    public void addNum(int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
        } else {
            upper.offer(num);
        }
        rebalance();
    }

    public double findMedian() {
        if (lower.size() == upper.size()) {
            return (lower.peek() + upper.peek()) / 2.0;
        }
        return lower.peek();
    }

    private void rebalance() {
        if (lower.size() < upper.size()) {
            lower.offer(upper.poll());
        } else if (lower.size() - upper.size() > 1) {
            upper.offer(lower.poll());
        }
    }
}
