package com.xujn.beauty.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 232. 用栈实现队列 (Implement Queue using Stacks)
 * 难度：简单
 *
 * 【题目描述】
 * 请你仅使用两个栈实现先入先出队列。
 *
 * 【解题思路：双栈 + 倒腾法】
 * - stackIn：专门负责入队操作（push 都往这里放）
 * - stackOut：专门负责出队操作（pop/peek 都从这里取）
 * 
 * 当 stackOut 空了需要出队时，把 stackIn 的东西全部逐个倒腾到 stackOut 里。
 * 这一倒腾的过程会让元素顺序翻转——正好把原来栈的 LIFO 变成了队列的 FIFO！
 *
 * 【均摊分析】
 * 虽然倒腾时单次操作是 O(n)，但每个元素最多被倒腾一次。
 * 所以每个元素的均摊时间复杂度是 O(1)。
 */
public class QueueUsingStacks {

    private Deque<Integer> stackIn;
    private Deque<Integer> stackOut;

    public QueueUsingStacks() {
        stackIn = new LinkedList<>();
        stackOut = new LinkedList<>();
    }

    // 入队永远往 stackIn 里面放
    public void push(int x) {
        stackIn.push(x);
    }

    // 出队：从 stackOut 取，如果 stackOut 空了就把 stackIn 全部倒过来
    public int pop() {
        if (stackOut.isEmpty()) {
            transfer();
        }
        return stackOut.pop();
    }

    // 查看队头：逻辑同 pop，只是 peek 不弹出
    public int peek() {
        if (stackOut.isEmpty()) {
            transfer();
        }
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // 把 stackIn 全部倒入 stackOut（LIFO -> FIFO 的魔法）
    private void transfer() {
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}
