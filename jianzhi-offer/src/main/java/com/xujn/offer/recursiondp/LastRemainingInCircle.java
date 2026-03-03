package com.xujn.offer.recursiondp;

/**
 * 圆圈中最后剩下的数字。
 */
public class LastRemainingInCircle {

    public int lastRemaining(int n, int m) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (result + m) % i;
        }
        return result;
    }
}
