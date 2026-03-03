package com.xujn.interviewguide.recursiondp;

/**
 * 编辑距离。
 */
public class EditDistance {

    public int minDistance(String first, String second) {
        if (first == null || second == null) {
            return 0;
        }
        int rows = first.length() + 1;
        int cols = second.length() + 1;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < cols; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                int replace = dp[i - 1][j - 1];
                if (first.charAt(i - 1) != second.charAt(j - 1)) {
                    replace++;
                }
                int insert = dp[i][j - 1] + 1;
                int delete = dp[i - 1][j] + 1;
                dp[i][j] = Math.min(replace, Math.min(insert, delete));
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
