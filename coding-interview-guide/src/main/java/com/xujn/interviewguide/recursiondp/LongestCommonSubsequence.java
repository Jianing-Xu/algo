package com.xujn.interviewguide.recursiondp;

/**
 * 最长公共子序列长度。
 */
public class LongestCommonSubsequence {

    public int length(String first, String second) {
        if (first == null || second == null || first.isEmpty() || second.isEmpty()) {
            return 0;
        }
        int rows = first.length();
        int cols = second.length();
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[rows][cols];
    }
}
