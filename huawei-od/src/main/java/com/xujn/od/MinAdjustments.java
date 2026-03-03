package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：最小调整次数
 *
 * 【题目描述】
 * 给定一个数组，每次操作可以将某个元素+1或-1。
 * 求最少多少次操作可以使得数组变成非递减序列。
 *
 * 【解题思路：贪心 + 优先队列】
 * 从左到右扫描，如果当前元素小于前一个元素：
 * 需要把前面的某些元素调小或把当前元素调大。
 * 使用大顶堆维护前面的值，如果堆顶 > 当前值，
 * 将堆顶替换为当前值（相当于将前面的值向当前值靠拢）。
 */
public class MinAdjustments {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // 大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long totalCost = 0;

        for (int i = 0; i < n; i++) {
            if (!maxHeap.isEmpty() && maxHeap.peek() > arr[i]) {
                totalCost += maxHeap.peek() - arr[i];
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
            maxHeap.offer(arr[i]);
        }

        System.out.println(totalCost);
    }
}
