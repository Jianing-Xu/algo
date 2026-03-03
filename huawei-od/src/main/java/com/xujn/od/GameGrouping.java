package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：游戏分组
 *
 * 【题目描述】
 * 部门准备举办团建活动。有N个员工，将他们分成两组，使得两组的实力尽量接近。
 * 每人有一个能力值，求两组实力差最小时的差值。
 *
 * 【解题思路：0-1背包变形】
 * 本质是将N个数分成两组，使两组之和的差最小。
 * 等价于：从N个数中选出若干个数，使得选出数之和尽量接近 totalSum/2。
 * 
 * 这就是一个经典的"背包问题"：
 * 背包容量是 totalSum/2，每个员工的能力值是物品重量。
 * dp[j] = true 表示可以凑出和为 j 的子集。
 * 找到能够达到的最大的 j（<= totalSum/2），答案 = totalSum - 2*j。
 */
public class GameGrouping {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] abilities = new int[n];
        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            abilities[i] = sc.nextInt();
            totalSum += abilities[i];
        }

        int half = totalSum / 2;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            // 倒序遍历防止重复使用同一个元素
            for (int j = half; j >= abilities[i]; j--) {
                if (dp[j - abilities[i]]) {
                    dp[j] = true;
                }
            }
        }

        // 从 half 开始找最大可达的和
        for (int j = half; j >= 0; j--) {
            if (dp[j]) {
                System.out.println(totalSum - 2 * j);
                return;
            }
        }
    }
}
