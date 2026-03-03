package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：跳房子
 *
 * 【题目描述】
 * 地上有一排格子，每个格子里面有一个数字，代表到达这个格子可以获得的金币数。
 * 小朋友从第0个格子出发，每次可以跳 1~K 步。
 * 问：到达最后一个格子时，最多可以收集多少金币？
 *
 * 【解题思路：动态规划】
 * dp[i] = 到达第i个格子时能获取的最大金币数
 * dp[i] = max(dp[j]) + coins[i]，其中 max(0, i-k) <= j < i
 *
 * 优化：可以用单调队列把 O(n*k) 优化到 O(n)，但大部分OD题 O(n*k) 已足够。
 */
public class HopscotchGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 格子数
        int k = sc.nextInt(); // 最大步长

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        dp[0] = coins[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MIN_VALUE;
            // 从前面 k 步的位置中选最大的
            for (int j = Math.max(0, i - k); j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i] += coins[i];
        }

        System.out.println(dp[n - 1]);
    }
}
