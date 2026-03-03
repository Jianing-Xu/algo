package com.xujn.interviewguide.recursiondp;

/**
 * 矩阵的最小路径和。
 */
public class MinPathSum {

    public int minPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols];
        dp[0] = matrix[0][0];
        for (int col = 1; col < cols; col++) {
            dp[col] = dp[col - 1] + matrix[0][col];
        }
        for (int row = 1; row < rows; row++) {
            dp[0] += matrix[row][0];
            for (int col = 1; col < cols; col++) {
                dp[col] = Math.min(dp[col], dp[col - 1]) + matrix[row][col];
            }
        }
        return dp[cols - 1];
    }
}
