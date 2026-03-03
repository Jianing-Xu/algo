package com.xujn.beauty.leetcode.recursion;

/**
 * LeetCode 70. 爬楼梯 (Climbing Stairs)
 * 难度：简单
 *
 * 【题目描述】
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 【解题思路】
 * 本质就是斐波那契数列！f(n) = f(n-1) + f(n-2)
 * 到第 n 阶的方法 = 从第 n-1 阶爬1步 + 从第 n-2 阶爬2步
 *
 * 用两个变量滚动计算即可，空间O(1)。
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev = 1, curr = 2;
        for (int i = 3; i <= n; i++) {
            int temp = curr;
            curr = prev + curr;
            prev = temp;
        }
        return curr;
    }
}
