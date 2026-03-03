package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：智能驾驶（最短路径绕障）
 *
 * 【题目描述】
 * 在NxN网格中，0表示可通行，1表示障碍物。
 * 从左上角(0,0)到右下角(n-1,n-1)，求最短路径步数。无法到达输出-1。
 *
 * 【解题思路：BFS 求最短路径】
 */
public class SmartDriving {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            System.out.println(-1);
            return;
        }

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // x, y, steps
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == n - 1 && cur[1] == n - 1) {
                System.out.println(cur[2]);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d], ny = cur[1] + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }
        System.out.println(-1);
    }
}
