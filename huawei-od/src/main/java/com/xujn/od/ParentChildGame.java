package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：亲子游戏
 *
 * 【题目描述】
 * 在NxN棋盘上，每个格子有一个糖果数。
 * 从左上角(0,0)出发只能向右或向下走,到右下角(n-1,n-1)。
 * 求路径上能收集的最大糖果数。
 *
 * 【解题思路：DP 路径最大和（同流浪地球）】
 */
public class ParentChildGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        int[][] dp = new int[n][n];
        dp[0][0] = grid[0][0];

        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < n; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];

        for (int i = 1; i < n; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];

        System.out.println(dp[n - 1][n - 1]);
    }
}
