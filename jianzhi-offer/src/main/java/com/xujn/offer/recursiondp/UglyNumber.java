package com.xujn.offer.recursiondp;

/**
 * 丑数。
 */
public class UglyNumber {

    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        for (int i = 1; i < n; i++) {
            int next = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            dp[i] = next;
            if (next == dp[p2] * 2) {
                p2++;
            }
            if (next == dp[p3] * 3) {
                p3++;
            }
            if (next == dp[p5] * 5) {
                p5++;
            }
        }
        return dp[n - 1];
    }
}
