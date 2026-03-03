package com.xujn.beauty.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 20. 有效的括号 (Valid Parentheses)
 * 难度：简单
 *
 * 【题目描述】
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 【解题思路：栈的经典应用 —— 括号匹配】
 * 遍历字符串，遇到左括号就入栈，遇到右括号就弹出栈顶看是否配对。
 * 最后栈空则所有括号全部配对成功。
 *
 * 时间复杂度：O(n)   空间复杂度：O(n)
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            // 遇到左括号，直接把对应的右括号压入栈中（小技巧：这样弹出时直接比较就行）
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // 遇到右括号：如果栈空了或弹出的不匹配，直接返回 false
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty(); // 全部处理完，栈要刚好空
    }
}
