package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：求符合条件元组个数
 *
 * 【题目描述】
 * 给定一个数组和一个目标值target，求满足 a + b + c = target 的三元组个数。
 * 数组中的元素可以重复使用。
 *
 * 【解题思路：固定一个数 + 双指针/哈希】
 * 排序后固定第一个数，对剩余部分用双指针扫描。
 * 注意允许重复元素时需要统计每个值的出现次数。
 */
public class TupleCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int target = sc.nextInt();

        Arrays.sort(arr);
        int count = 0;

        for (int i = 0; i < n; i++) {
            int left = i, right = n - 1;
            while (left <= right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    count++;
                    // 处理重复
                    if (left == right) break;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(count);
    }
}
