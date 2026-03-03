package com.xujn.interviewguide.recursiondp;

/**
 * 最长公共子串。
 */
public class LongestCommonSubstring {

    public String longest(String first, String second) {
        if (first == null || second == null || first.isEmpty() || second.isEmpty()) {
            return "";
        }
        int[][] dp = new int[first.length()][second.length()];
        int maxLen = 0;
        int end = 0;
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    dp[i][j] = (i == 0 || j == 0) ? 1 : dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        end = i;
                    }
                }
            }
        }
        return first.substring(end - maxLen + 1, end + 1);
    }
}
