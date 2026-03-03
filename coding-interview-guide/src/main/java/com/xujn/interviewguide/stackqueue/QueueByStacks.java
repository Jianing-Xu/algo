package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * 用两个栈实现队列。
 */
public class QueueByStacks {

    private final Deque<Integer> pushStack = new ArrayDeque<>();
    private final Deque<Integer> popStack = new ArrayDeque<>();

    public void offer(int value) {
        pushStack.push(value);
    }

    public int poll() {
        moveIfNecessary();
        if (popStack.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return popStack.pop();
    }

    public int peek() {
        moveIfNecessary();
        if (popStack.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return popStack.peek();
    }

    private void moveIfNecessary() {
        if (!popStack.isEmpty()) {
            return;
        }
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }
}
