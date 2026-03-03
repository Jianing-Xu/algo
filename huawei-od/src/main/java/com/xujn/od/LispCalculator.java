package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：仿LISP运算
 *
 * 【题目描述】
 * 仿LISP运算，表达式格式为 (operation arg1 arg2)，支持 add、sub、mul、div。
 * 参数可以是数字，也可以是嵌套的子表达式。
 * 示例：(add 1 (mul 2 3)) = 1 + 2*3 = 7
 *
 * 【解题思路：递归解析 / 栈解析】
 * 遇到左括号开始新的表达式，遇到右括号结束当前表达式并计算结果。
 */
public class LispCalculator {

    static int pos = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine().trim();
        pos = 0;
        System.out.println(parse(expr));
    }

    private static int parse(String s) {
        if (s.charAt(pos) == '(') {
            pos++; // 跳过 '('
            // 读取操作符
            StringBuilder opBuf = new StringBuilder();
            while (s.charAt(pos) != ' ') {
                opBuf.append(s.charAt(pos++));
            }
            String op = opBuf.toString();
            pos++; // 跳过空格

            // 读第一个参数
            int arg1 = parseArg(s);
            pos++; // 跳过空格

            // 读第二个参数
            int arg2 = parseArg(s);
            pos++; // 跳过 ')'

            switch (op) {
                case "add": return arg1 + arg2;
                case "sub": return arg1 - arg2;
                case "mul": return arg1 * arg2;
                case "div": return arg1 / arg2;
                default: return 0;
            }
        }
        return 0;
    }

    private static int parseArg(String s) {
        if (s.charAt(pos) == '(') {
            return parse(s);
        } else {
            // 读取数字（可能带负号）
            StringBuilder numBuf = new StringBuilder();
            while (pos < s.length() && s.charAt(pos) != ' ' && s.charAt(pos) != ')') {
                numBuf.append(s.charAt(pos++));
            }
            return Integer.parseInt(numBuf.toString());
        }
    }
}
