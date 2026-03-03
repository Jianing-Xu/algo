package com.xujn.interviewguide.recursiondp;

/**
 * 排成一条线的纸牌博弈。
 */
public class CardGameWinner {

    public int winnerScore(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] first = new int[n][n];
        int[][] second = new int[n][n];
        for (int i = 0; i < n; i++) {
            first[i][i] = arr[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left + len - 1 < n; left++) {
                int right = left + len - 1;
                first[left][right] = Math.max(arr[left] + second[left + 1][right],
                        arr[right] + second[left][right - 1]);
                second[left][right] = Math.min(first[left + 1][right], first[left][right - 1]);
            }
        }
        return Math.max(first[0][n - 1], second[0][n - 1]);
    }
}
