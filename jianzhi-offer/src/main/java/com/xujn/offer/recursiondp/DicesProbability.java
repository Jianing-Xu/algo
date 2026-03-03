package com.xujn.offer.recursiondp;

/**
 * n 个骰子的点数。
 */
public class DicesProbability {

    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        for (int i = 0; i < 6; i++) {
            dp[i] = 1.0 / 6.0;
        }
        for (int dice = 2; dice <= n; dice++) {
            double[] next = new double[5 * dice + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int face = 1; face <= 6; face++) {
                    next[i + face - 1] += dp[i] / 6.0;
                }
            }
            dp = next;
        }
        return dp;
    }
}
