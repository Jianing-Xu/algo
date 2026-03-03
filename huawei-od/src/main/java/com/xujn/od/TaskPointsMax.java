package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：执行任务赚积分
 *
 * 【题目描述】
 * 有N个任务，每个任务有一个截止时间和完成后的积分奖励。
 * 每个时刻只能做一个任务，每个任务恰好消耗一个时间单位。
 * 求能获得的最大积分。
 *
 * 【解题思路：贪心 + 优先队列（反悔贪心）】
 * 1. 按截止时间排序
 * 2. 使用小顶堆维护已选择任务的积分
 * 3. 遍历任务：
 *    - 如果当前时间还有空位（堆大小 < 截止时间），直接放进堆
 *    - 如果满了，但当前任务积分大于堆顶（已选的最小积分），替换它（反悔操作）
 * 4. 堆内所有积分之和就是答案
 */
public class TaskPointsMax {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] tasks = new int[n][2]; // [截止时间, 积分]

        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextInt(); // 截止时间
            tasks[i][1] = sc.nextInt(); // 积分
        }

        // 按截止时间升序排序
        Arrays.sort(tasks, (a, b) -> a[0] - b[0]);

        // 小顶堆，按积分排序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] task : tasks) {
            int deadline = task[0];
            int points = task[1];

            if (minHeap.size() < deadline) {
                // 还有空位，直接加入
                minHeap.offer(points);
            } else if (!minHeap.isEmpty() && minHeap.peek() < points) {
                // 已满，但当前任务积分更高，替换掉积分最低的
                minHeap.poll();
                minHeap.offer(points);
            }
        }

        long total = 0;
        for (int p : minHeap) {
            total += p;
        }
        System.out.println(total);
    }
}
