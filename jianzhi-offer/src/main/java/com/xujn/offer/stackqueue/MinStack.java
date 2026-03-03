package com.xujn.offer.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 包含 min 函数的栈。
 */
public class MinStack {

    private final Deque<Integer> dataStack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int value) {
        dataStack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public int pop() {
        minStack.pop();
        return dataStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
