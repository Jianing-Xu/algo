package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：高效的任务规划
 *
 * 【题目描述】
 * 有N个任务需要在一台机器上执行。每个任务有一个执行时间和一个截止时间。
 * 任务一旦开始不可中断。机器同一时刻只能执行一个任务。
 * 求最优的任务执行顺序，使得所有任务的延迟惩罚之和最小。
 * 延迟惩罚 = max(0, 完成时间 - 截止时间)
 *
 * 【解题思路：贪心 —— 按截止时间排序】
 * 这是经典的加权作业调度问题的简化版。
 * 按照截止时间升序排列，优先执行截止时间早的任务，可以最小化总延迟。
 *
 * 如果截止时间相同，优先执行耗时短的任务（SPT规则）。
 */
public class EfficientTaskPlanning {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] tasks = new int[n][2]; // [执行时间, 截止时间]
        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextInt(); // 执行时间
            tasks[i][1] = sc.nextInt(); // 截止时间
        }

        // 按截止时间升序排列，截止时间相同按执行时间升序
        Arrays.sort(tasks, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });

        long totalPenalty = 0;
        long currentTime = 0;

        for (int[] task : tasks) {
            currentTime += task[0]; // 执行完当前任务所需时间
            long delay = Math.max(0, currentTime - task[1]);
            totalPenalty += delay;
        }

        System.out.println(totalPenalty);
    }
}
