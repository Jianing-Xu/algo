package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：模拟工作队列
 *
 * 【题目描述】
 * 有M台机器和N个任务。每个机器同时只能处理一个任务。
 * 任务按到达顺序分配给最早空闲的机器。如果有多台空闲，分配给编号最小的。
 * 每个任务有处理耗时。输出每个任务的完成时间。
 *
 * 【解题思路：优先队列模拟 (小顶堆)】
 */
public class WorkQueueSimulation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(); // 机器数
        int n = sc.nextInt(); // 任务数

        int[] taskTime = new int[n];
        for (int i = 0; i < n; i++) taskTime[i] = sc.nextInt();

        // 小顶堆：[完成时间, 机器编号]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1]; // 完成时间相同，编号小的优先
        });

        // 初始时所有机器都空闲（完成时间=0）
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{0, i});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int[] machine = pq.poll();
            int finishTime = machine[0] + taskTime[i];
            if (i > 0) sb.append(" ");
            sb.append(finishTime);
            pq.offer(new int[]{finishTime, machine[1]});
        }
        System.out.println(sb.toString());
    }
}
