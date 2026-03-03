package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：乘坐保密电梯
 *
 * 【题目描述】
 * 保密电梯只能按特定按钮组合到达目标楼层。
 * 电梯从0层出发，给定N个按钮，每个按钮可以让电梯上升或下降固定层数。
 * 求从0层到目标层target的最少按键次数。如果无法到达，输出-1。
 *
 * 【解题思路：BFS 求最短路径】
 * 将每层楼看作图中的一个节点，每个按钮看作一条边。
 * BFS从0出发，找到target的最短路径（即最少按键次数）。
 */
public class SecretElevator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] buttons = new int[n];
        for (int i = 0; i < n; i++) buttons[i] = sc.nextInt();
        int target = sc.nextInt();
        int maxFloor = 1000; // 假设最大楼层范围

        boolean[] visited = new boolean[maxFloor + 1];
        Queue<int[]> queue = new LinkedList<>(); // [当前楼层, 按键次数]
        queue.offer(new int[]{0, 0});
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int floor = cur[0], steps = cur[1];

            if (floor == target) {
                System.out.println(steps);
                return;
            }

            for (int btn : buttons) {
                int nextFloor = floor + btn;
                if (nextFloor >= 0 && nextFloor <= maxFloor && !visited[nextFloor]) {
                    visited[nextFloor] = true;
                    queue.offer(new int[]{nextFloor, steps + 1});
                }
            }
        }

        System.out.println(-1);
    }
}
