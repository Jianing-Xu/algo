package com.xujn.offer.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 队列的最大值。
 */
public class MaxQueue {

    private final Queue<Integer> queue = new ArrayDeque<>();
    private final Deque<Integer> deque = new ArrayDeque<>();

    public int maxValue() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void pushBack(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public int popFront() {
        if (queue.isEmpty()) {
            return -1;
        }
        int value = queue.poll();
        if (value == deque.peekFirst()) {
            deque.pollFirst();
        }
        return value;
    }
}
