package com.xujn.offer.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈的压入、弹出序列。
 */
public class ValidateStackSequences {

    public boolean validate(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int popIndex = 0;
        for (int value : pushed) {
            stack.push(value);
            while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}
