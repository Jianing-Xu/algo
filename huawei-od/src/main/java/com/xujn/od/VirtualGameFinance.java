package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：虚拟游戏理财
 *
 * 【题目描述】
 * 游戏中有N种理财产品，每种有投资金额和收益。
 * 玩家有总金额M，每种产品只能买一份。求最大收益。
 *
 * 【解题思路：0-1背包】
 */
public class VirtualGameFinance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 产品数
        int m = sc.nextInt(); // 总金额

        int[] cost = new int[n];
        int[] profit = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = sc.nextInt();
            profit[i] = sc.nextInt();
        }

        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + profit[i]);
            }
        }
        System.out.println(dp[m]);
    }
}
