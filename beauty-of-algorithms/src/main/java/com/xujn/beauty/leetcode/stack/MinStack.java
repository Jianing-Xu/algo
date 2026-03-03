package com.xujn.beauty.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 155. 最小栈 (Min Stack)
 * 难度：中等
 *
 * 【题目描述】
 * 设计一个支持 push、pop、top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 【解题思路：双栈法（辅助栈同步压入当前最小值）】
 * 维护两个栈：
 * - 数据栈 dataStack：正常存放所有数据。
 * - 最小栈 minStack：栈顶永远保存当前数据栈中的最小值。
 * 
 * 每次 push 时，同时往 minStack 压入 Math.min(当前值, minStack栈顶)。
 * 每次 pop 时，两个栈同步弹出。
 * 这样 getMin() 直接看 minStack 的栈顶就行了，时间复杂度 O(1)！
 *
 * 时间复杂度：所有操作均为 O(1)   空间复杂度：O(n)
 */
public class MinStack {

    private Deque<Integer> dataStack;
    private Deque<Integer> minStack;

    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE); // 哨兵，防止初始时 peek 空栈
    }

    public void push(int val) {
        dataStack.push(val);
        // 辅助栈同步入压：压入 "当前值 与 辅助栈栈顶" 中较小的那个
        minStack.push(Math.min(val, minStack.peek()));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop(); // 同步弹出
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek(); // O(1) 获取最小值
    }
}
