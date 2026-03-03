package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：采购订单（最小花费凑齐货物）
 *
 * 【题目描述】
 * 有N种货物需要采购，每种货物有多个供应商提供不同价格。
 * 从每种货物中选一个供应商，求最小总花费。
 *
 * 【解题思路：贪心——每种货物选最便宜的供应商】
 */
public class ProcurementOrder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 货物种类数
        long totalCost = 0;

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt(); // 该货物的供应商数
            int minPrice = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                int price = sc.nextInt();
                minPrice = Math.min(minPrice, price);
            }
            totalCost += minPrice;
        }

        System.out.println(totalCost);
    }
}
