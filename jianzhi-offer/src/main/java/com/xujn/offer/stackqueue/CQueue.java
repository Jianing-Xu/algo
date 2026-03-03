package com.xujn.offer.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用两个栈实现队列。
 */
public class CQueue {

    private final Deque<Integer> inStack = new ArrayDeque<>();
    private final Deque<Integer> outStack = new ArrayDeque<>();

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        moveIfNecessary();
        return outStack.isEmpty() ? -1 : outStack.pop();
    }

    private void moveIfNecessary() {
        if (!outStack.isEmpty()) {
            return;
        }
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
