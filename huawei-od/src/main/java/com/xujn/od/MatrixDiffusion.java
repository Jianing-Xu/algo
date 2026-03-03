package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：矩阵扩散
 *
 * 【题目描述】
 * 给定一个 m*n 的矩阵，某些位置初始值为1，其余为0。
 * 每一秒，值为1的格子会向上下左右四个方向扩散（将相邻的0变为1）。
 * 问经过多少秒后，所有格子都变为1？
 *
 * 【解题思路：多源BFS（层序遍历）】
 * 经典的"腐烂的橘子"问题变形。
 * 将所有初始为1的格子加入队列，进行多源BFS层序遍历，层数就是答案。
 */
public class MatrixDiffusion {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean expanded = false;

            for (int s = 0; s < size; s++) {
                int[] pos = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = pos[0] + dx[d];
                    int ny = pos[1] + dy[d];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                        grid[nx][ny] = 1;
                        queue.offer(new int[]{nx, ny});
                        expanded = true;
                    }
                }
            }

            if (expanded) time++;
        }

        System.out.println(time);
    }
}
