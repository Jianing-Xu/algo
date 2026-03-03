package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：网上商城优惠活动（购物凑满减）
 *
 * 【题目描述】
 * 商城促销活动，消费满X元减Y元。给定N个商品价格和满减门槛X，
 * 求：从N个商品中选若干件，使得总价 >= X 且总价最接近X（即超出最少）。
 * 输出最小的满足条件的总价。如果所有物品总价都不够X，输出-1。
 *
 * 【解题思路：0-1背包变体】
 * 类似子集和问题。用dp记录哪些总价可以被凑出来。
 * 从 >= X 的最小值开始找。
 */
public class ShoppingDiscount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt(); // 满减门槛

        int[] prices = new int[n];
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
            totalSum += prices[i];
        }

        if (totalSum < x) {
            System.out.println(-1);
            return;
        }

        // dp[j] = true 表示可以从商品中凑出总价恰好为 j
        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = totalSum; j >= prices[i]; j--) {
                if (dp[j - prices[i]]) {
                    dp[j] = true;
                }
            }
        }

        // 从 x 开始找最小可达的总价
        for (int j = x; j <= totalSum; j++) {
            if (dp[j]) {
                System.out.println(j);
                return;
            }
        }

        System.out.println(-1);
    }
}
