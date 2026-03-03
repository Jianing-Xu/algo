package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：分苹果
 *
 * 【题目描述】
 * 有M个苹果分给N个人，每人至少1个苹果，求有多少种分法。
 *
 * 【解题思路：隔板法 / 递归】
 * 经典组合数学：将M个苹果分给N个人每人至少1个，
 * 等价于在M-1个间隔中插入N-1个隔板，即 C(M-1, N-1)。
 */
public class DivideApples {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // 苹果数
        int n = sc.nextInt(); // 人数

        if (m < n) {
            System.out.println(0);
            return;
        }
        System.out.println(combination(m - 1, n - 1));
    }

    // 计算组合数 C(n, k)
    private static long combination(int n, int k) {
        if (k > n - k) k = n - k; // 优化：C(n,k) = C(n, n-k)
        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }
}
