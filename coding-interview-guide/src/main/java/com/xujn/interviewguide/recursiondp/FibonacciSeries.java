package com.xujn.interviewguide.recursiondp;

/**
 * 斐波那契数列及其常见递推形式。
 */
public class FibonacciSeries {

    public int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int prev = 1;
        int current = 1;
        for (int i = 3; i <= n; i++) {
            int next = prev + current;
            prev = current;
            current = next;
        }
        return current;
    }

    public int climbStairs(int n) {
        return fibonacci(n + 1);
    }
}
