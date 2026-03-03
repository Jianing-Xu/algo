package com.xujn.offer.array;

/**
 * 礼物的最大价值。
 */
public class MaxValueInGrid {

    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] dp = new int[grid[0].length];
        for (int[] row : grid) {
            for (int col = 0; col < row.length; col++) {
                int left = col > 0 ? dp[col - 1] : 0;
                int up = dp[col];
                dp[col] = Math.max(left, up) + row[col];
            }
        }
        return dp[grid[0].length - 1];
    }
}
