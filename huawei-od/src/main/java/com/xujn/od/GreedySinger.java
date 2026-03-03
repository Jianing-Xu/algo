package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：贪心歌手
 *
 * 【题目描述】
 * 一位歌手要在有限的时间T内演唱歌曲获得最大人气。
 * 有N首歌，每首歌有演唱时间time[i]和人气值popularity[i]。
 * 每首歌最多唱一次。求在时间T内能获得的最大人气值。
 *
 * 【解题思路：0-1背包问题】
 * 时间T就是背包容量，歌的演唱时间就是物品重量，人气值就是物品价值。
 */
public class GreedySinger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt(); // 总时间
        int[] time = new int[n];
        int[] pop = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
            pop[i] = sc.nextInt();
        }

        // 一维滚动数组 0-1背包
        int[] dp = new int[t + 1];
        for (int i = 0; i < n; i++) {
            for (int j = t; j >= time[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - time[i]] + pop[i]);
            }
        }

        System.out.println(dp[t]);
    }
}
