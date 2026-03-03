package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：购物（最优组合购买）
 *
 * 【题目描述】
 * 商店有N件商品，给定预算budget。
 * 从中选取若干件商品使总价不超过预算且总价最大。输出最大总价。
 *
 * 【解题思路：0-1背包】
 */
public class Shopping {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int budget = sc.nextInt();
        int[] price = new int[n];
        for (int i = 0; i < n; i++) price[i] = sc.nextInt();

        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            for (int j = budget; j >= price[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - price[i]] + price[i]);
            }
        }
        System.out.println(dp[budget]);
    }
}
