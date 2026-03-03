package com.xujn.interviewguide.recursiondp;

import java.util.Arrays;

/**
 * 换钱的最少货币数。
 */
public class MinCoins {

    public int minCoins(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim < 0) {
            return -1;
        }
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int rest = coin; rest <= aim; rest++) {
                if (dp[rest - coin] != Integer.MAX_VALUE) {
                    dp[rest] = Math.min(dp[rest], dp[rest - coin] + 1);
                }
            }
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }
}
