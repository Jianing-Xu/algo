package com.xujn.interviewguide.recursiondp;

/**
 * 布尔表达式期望结果的方法数。
 */
public class BooleanExpressionWays {

    public int countWays(String expression, boolean desired) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }
        char[] chars = expression.toCharArray();
        int n = chars.length;
        int[][] trueDp = new int[n][n];
        int[][] falseDp = new int[n][n];
        for (int i = 0; i < n; i += 2) {
            trueDp[i][i] = chars[i] == '1' ? 1 : 0;
            falseDp[i][i] = chars[i] == '0' ? 1 : 0;
        }
        for (int len = 3; len <= n; len += 2) {
            for (int left = 0; left + len - 1 < n; left += 2) {
                int right = left + len - 1;
                for (int split = left + 1; split < right; split += 2) {
                    int lt = trueDp[left][split - 1];
                    int lf = falseDp[left][split - 1];
                    int rt = trueDp[split + 1][right];
                    int rf = falseDp[split + 1][right];
                    switch (chars[split]) {
                        case '&' -> {
                            trueDp[left][right] += lt * rt;
                            falseDp[left][right] += lt * rf + lf * rt + lf * rf;
                        }
                        case '|' -> {
                            trueDp[left][right] += lt * rt + lt * rf + lf * rt;
                            falseDp[left][right] += lf * rf;
                        }
                        case '^' -> {
                            trueDp[left][right] += lt * rf + lf * rt;
                            falseDp[left][right] += lt * rt + lf * rf;
                        }
                        default -> throw new IllegalArgumentException("invalid operator");
                    }
                }
            }
        }
        return desired ? trueDp[0][n - 1] : falseDp[0][n - 1];
    }
}
