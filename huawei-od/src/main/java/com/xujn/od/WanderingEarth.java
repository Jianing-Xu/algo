package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：流浪地球 —— 近似引力弹弓计算
 *
 * 【题目描述】
 * 流浪地球在宇宙航行中需要借助星球的引力弹弓效应加速。
 * 给定一个二维矩阵 space[m][n]，每个值代表该空间区域内星球的引力值。
 * 地球从左上角 (0,0) 出发，要到达右下角 (m-1,n-1)。
 * 地球只能向右或向下移动。求地球沿途获得的最大引力加速值之和。
 *
 * 【解题思路：动态规划 —— 路径最大和】
 * 这是一个标准的"网格路径最大和"DP问题。
 * dp[i][j] = 从(0,0)到(i,j)能获得的最大引力和
 * dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + space[i][j]
 *
 * 时间复杂度：O(m*n)   空间复杂度：O(m*n)，可优化为O(n)
 */
public class WanderingEarth {

    public static int maxGravity(int[][] space) {
        if (space == null || space.length == 0) return 0;
        int m = space.length, n = space[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = space[0][0];

        // 初始化第一行（只能从左到右）
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + space[0][j];
        }

        // 初始化第一列（只能从上到下）
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + space[i][0];
        }

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + space[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] space = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                space[i][j] = sc.nextInt();
        System.out.println(maxGravity(space));
    }
}
