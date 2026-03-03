package com.xujn.offer.recursiondp;

/**
 * 青蛙跳台阶问题。
 */
public class NumWays {

    public int numWays(int n) {
        if (n < 2) {
            return 1;
        }
        int mod = 1_000_000_007;
        int prev = 1;
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int next = (prev + current) % mod;
            prev = current;
            current = next;
        }
        return current;
    }
}
