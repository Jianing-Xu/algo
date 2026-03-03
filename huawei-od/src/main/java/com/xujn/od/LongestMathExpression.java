package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：提取字符串中的最长数学表达式并计算
 *
 * 【题目描述】
 * 给定一个字符串，从中提取出最长的合法数学表达式（只含 +、-、*、数字），
 * 计算该表达式的值并输出。
 * 
 * 规则：
 * - 表达式仅包含 0-9、+、-、*
 * - 表达式不以运算符开头或结尾
 * - 不存在连续的运算符
 * - 找出最长的合法表达式，如果长度相同返回最先出现的
 *
 * 【解题思路】
 * 1. 遍历字符串，使用滑动窗口提取所有可能的合法表达式子串
 * 2. 维护最长合法表达式
 * 3. 利用双栈或递归下降法计算表达式值（需处理运算符优先级）
 */
public class LongestMathExpression {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();

        String longest = "";

        // 提取所有可能的合法数学表达式
        int n = s.length();
        int i = 0;
        while (i < n) {
            // 跳过非数字字符找到表达式起点
            if (!Character.isDigit(s.charAt(i))) {
                i++;
                continue;
            }

            // 从 i 开始向后扩展合法表达式
            int j = i;
            while (j < n && (Character.isDigit(s.charAt(j)) || s.charAt(j) == '+' 
                    || s.charAt(j) == '-' || s.charAt(j) == '*')) {
                j++;
            }

            // 提取候选子串 s[i..j-1]，但需确保不以运算符结尾
            String candidate = s.substring(i, j);
            // 去掉末尾的运算符
            while (candidate.length() > 0 && !Character.isDigit(candidate.charAt(candidate.length() - 1))) {
                candidate = candidate.substring(0, candidate.length() - 1);
            }

            if (candidate.length() > longest.length()) {
                longest = candidate;
            }

            i = j;
        }

        if (longest.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(calculate(longest));
        }
    }

    /**
     * 计算数学表达式的值（支持 +、-、*，处理运算符优先级）
     * 使用栈来处理：
     * - 遇到 * 时立即与栈顶计算（因为 * 优先级高）
     * - 遇到 +/- 时将当前数（带符号）入栈
     * - 最后将栈中所有数求和
     */
    public static long calculate(String expr) {
        Deque<Long> stack = new ArrayDeque<>();
        long num = 0;
        char sign = '+'; // 当前数字前面的运算符

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (!Character.isDigit(c) || i == expr.length() - 1) {
                switch (sign) {
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': stack.push(stack.pop() * num); break;
                }
                sign = c;
                num = 0;
            }
        }

        long result = 0;
        for (long val : stack) {
            result += val;
        }
        return result;
    }
}
