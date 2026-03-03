package com.xujn.interviewguide.recursiondp;

/**
 * 判断 aim 是否由两个字符串交错组成。
 */
public class StringInterleaving {

    public boolean isInterleave(String first, String second, String aim) {
        if (first == null || second == null || aim == null) {
            return false;
        }
        if (first.length() + second.length() != aim.length()) {
            return false;
        }
        boolean[][] dp = new boolean[first.length() + 1][second.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= first.length(); i++) {
            dp[i][0] = dp[i - 1][0] && first.charAt(i - 1) == aim.charAt(i - 1);
        }
        for (int j = 1; j <= second.length(); j++) {
            dp[0][j] = dp[0][j - 1] && second.charAt(j - 1) == aim.charAt(j - 1);
        }
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                char target = aim.charAt(i + j - 1);
                dp[i][j] = (dp[i - 1][j] && first.charAt(i - 1) == target)
                        || (dp[i][j - 1] && second.charAt(j - 1) == target);
            }
        }
        return dp[first.length()][second.length()];
    }
}
