package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：高矮个子排队
 *
 * 【题目描述】
 * N个人站成一排，给出每个人的身高。
 * 要求重新排列，使得相邻两人高矮交替排列（一高一矮）。
 * 输出重排后的结果。
 *
 * 【解题思路：模拟/排序 + 穿插】
 * 1. 先将数组排序
 * 2. 从第二个位置开始，每隔一个位置，将较大的元素与相邻元素交换
 *    使得偶数位置（0-indexed）的元素小于奇数位置的元素
 *
 * 或者更简单的方法：排序后，相邻不满足条件就交换。
 */
public class HeightQueue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        Arrays.sort(heights);

        // 从第1个位置开始，每两个一组交换（使奇数位 > 偶数位）
        for (int i = 1; i < n - 1; i += 2) {
            int temp = heights[i];
            heights[i] = heights[i + 1];
            heights[i + 1] = temp;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(" ");
            sb.append(heights[i]);
        }
        System.out.println(sb.toString());
    }
}
