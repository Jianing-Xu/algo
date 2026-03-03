package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：Wonderland（游乐场门票优化）
 *
 * 【题目描述】
 * Wonderland是一个奇幻游乐场，门票有两种：单日票price[i]，或连续j天通票costs[j]。
 * 给定你想去的日期列表days[]和票价方案，求最小花费。
 * 类似 LeetCode 983 "最低票价"。
 *
 * 【解题思路：动态规划】
 * dp[i] = 覆盖到第i天时的最小花费。
 * 对于每一天，如果这天不需要去就沿用前一天dp值；
 * 如果需要去，分别尝试买1日票、7日票、30日票，取最小值。
 */
public class Wonderland {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] days = new int[n];
        for (int i = 0; i < n; i++) days[i] = sc.nextInt();

        int cost1 = sc.nextInt(); // 1日票价格
        int cost7 = sc.nextInt(); // 7日通票价格
        int cost30 = sc.nextInt(); // 30日通票价格

        Set<Integer> daySet = new HashSet<>();
        for (int d : days) daySet.add(d);

        int lastDay = days[n - 1];
        int[] dp = new int[lastDay + 1];

        for (int i = 1; i <= lastDay; i++) {
            if (!daySet.contains(i)) {
                dp[i] = dp[i - 1]; // 这天不去，无需额外花费
            } else {
                int c1 = dp[i - 1] + cost1;
                int c7 = dp[Math.max(0, i - 7)] + cost7;
                int c30 = dp[Math.max(0, i - 30)] + cost30;
                dp[i] = Math.min(c1, Math.min(c7, c30));
            }
        }

        System.out.println(dp[lastDay]);
    }
}
