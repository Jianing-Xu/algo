package com.xujn.offer.recursiondp;

/**
 * 斐波那契数列。
 */
public class FibonacciSequence {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int prev = 0;
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + current;
            prev = current;
            current = next;
        }
        return current;
    }
}
