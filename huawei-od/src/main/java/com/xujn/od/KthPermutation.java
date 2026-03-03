package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：第K个排列
 *
 * 【题目描述】
 * 给定一个正整数n和k，输出1~n的全排列中按字典序排列的第k个排列。
 *
 * 【解题思路：数学推导（康托展开的逆运算）】
 * 利用阶乘来确定每一位应该填的数字。
 * 
 * 例如 n=3, k=3：
 * 全排列按字典序：123, 132, 213, 231, 312, 321
 * 第3个排列是 213
 *
 * 推导过程：
 * - 第一位：k-1=2, 2/(2!) = 1余0 → 在剩余数字[1,2,3]中取第1个(0-indexed)即2
 * - 第二位：余数=0, 0/(1!) = 0余0 → 在剩余数字[1,3]中取第0个即1
 * - 第三位：余数=0 → 取剩余的3
 * 结果：213
 */
public class KthPermutation {

    public static String getPermutation(int n, int k) {
        // 构建可用数字列表
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);

        // 预计算阶乘
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        k--; // 转为 0-indexed

        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            int index = k / factorial[i - 1]; // 确定当前位选第几个
            sb.append(nums.get(index));
            nums.remove(index); // 已使用，从列表移除
            k %= factorial[i - 1]; // 更新余数
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(getPermutation(n, k));
    }
}
