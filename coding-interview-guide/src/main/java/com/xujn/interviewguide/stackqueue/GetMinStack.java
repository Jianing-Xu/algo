package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * 设计一个有 getMin 功能的栈。
 */
public class GetMinStack {

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
        if (dataStack.isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        minStack.pop();
        return dataStack.pop();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        return minStack.peek();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }
}
