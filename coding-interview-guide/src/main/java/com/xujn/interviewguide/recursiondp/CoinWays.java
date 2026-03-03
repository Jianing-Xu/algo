package com.xujn.interviewguide.recursiondp;

/**
 * 换钱的方法数。
 */
public class CoinWays {

    public int ways(int[] coins, int aim) {
        if (coins == null || coins.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int rest = coin; rest <= aim; rest++) {
                dp[rest] += dp[rest - coin];
            }
        }
        return dp[aim];
    }
}
