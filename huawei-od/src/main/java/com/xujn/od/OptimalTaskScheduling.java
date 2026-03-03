package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：任务最优调度
 *
 * 【题目描述】
 * 有N个独立任务需要在两台机器上并行执行。每个任务有一个执行时间。
 * 一个任务只能分配到一台机器上，求完成所有任务的最短时间。
 *
 * 【解题思路：DP（子集和问题变形）】
 * 等价于将任务分成两组，使两组执行时间之差最小（类似游戏分组题）。
 * 最短时间 = max(sum_group1, sum_group2) = (totalSum + diff) / 2
 * 最小diff => 最优调度。
 */
public class OptimalTaskScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tasks = new int[n];
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            tasks[i] = sc.nextInt();
            totalSum += tasks[i];
        }

        int half = totalSum / 2;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = half; j >= tasks[i]; j--) {
                if (dp[j - tasks[i]]) dp[j] = true;
            }
        }

        // 找最接近 half 的可达值
        for (int j = half; j >= 0; j--) {
            if (dp[j]) {
                int group1 = j;
                int group2 = totalSum - j;
                System.out.println(Math.max(group1, group2));
                return;
            }
        }
    }
}
