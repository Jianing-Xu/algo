package com.xujn.offer.design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 字符流中第一个只出现一次的字符。
 */
public class FirstUniqueCharacterStream {

    private final int[] counts = new int[256];
    private final Deque<Character> queue = new ArrayDeque<>();

    public void insert(char ch) {
        counts[ch]++;
        queue.offer(ch);
        while (!queue.isEmpty() && counts[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public char firstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
