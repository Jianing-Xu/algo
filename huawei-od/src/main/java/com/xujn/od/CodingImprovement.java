package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：编码能力提升计划
 *
 * 【题目描述】
 * 有N天，每天可以选择完成若干道编程题来提升编码能力。
 * 第i天的题目难度为difficulty[i]，完成可以获得的能力提升值为value[i]。
 * 但是连续做题会疲劳，每天最多只能做一道，且不能连续两天都做题。
 * 求N天内能获得的最大能力提升值。
 *
 * 【解题思路：动态规划（打家劫舍变形）】
 * dp[i][0] = 第i天不做题时的最大提升
 * dp[i][1] = 第i天做题时的最大提升
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1])
 * dp[i][1] = dp[i-1][0] + value[i]
 */
public class CodingImprovement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] value = new int[n];
        for (int i = 0; i < n; i++) value[i] = sc.nextInt();

        if (n == 0) { System.out.println(0); return; }
        if (n == 1) { System.out.println(value[0]); return; }

        int notDo = 0;        // dp[i][0]
        int doIt = value[0];   // dp[i][1]

        for (int i = 1; i < n; i++) {
            int newNotDo = Math.max(notDo, doIt);
            int newDoIt = notDo + value[i];
            notDo = newNotDo;
            doIt = newDoIt;
        }

        System.out.println(Math.max(notDo, doIt));
    }
}
