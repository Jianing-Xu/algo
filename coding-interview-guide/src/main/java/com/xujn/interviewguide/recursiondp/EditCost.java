package com.xujn.interviewguide.recursiondp;

/**
 * 最小编辑代价。
 */
public class EditCost {

    public int minCost(String first, String second, int insertCost, int deleteCost, int replaceCost) {
        if (first == null || second == null) {
            return 0;
        }
        int[][] dp = new int[first.length() + 1][second.length() + 1];
        for (int i = 1; i <= first.length(); i++) {
            dp[i][0] = i * deleteCost;
        }
        for (int j = 1; j <= second.length(); j++) {
            dp[0][j] = j * insertCost;
        }
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                int replace = dp[i - 1][j - 1] + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : replaceCost);
                int insert = dp[i][j - 1] + insertCost;
                int delete = dp[i - 1][j] + deleteCost;
                dp[i][j] = Math.min(replace, Math.min(insert, delete));
            }
        }
        return dp[first.length()][second.length()];
    }
}
