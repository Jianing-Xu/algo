package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：寻找最大价值矿堆
 *
 * 【题目描述】
 * 给定一个m*n的矩阵，矩阵中的值代表矿石数量（0表示无矿）。
 * 相邻的（上下左右）非零格子构成一个矿堆。
 * 求所有矿堆中价值（矿石数量之和）最大的矿堆的价值。
 *
 * 【解题思路：BFS / DFS 求连通分量】
 * 经典的"岛屿问题"变形：
 * 1. 遍历矩阵，遇到非零且未访问的格子就启动 BFS/DFS
 * 2. 在BFS中遍历整个连通分量，累加矿石数量
 * 3. 记录最大值
 */
public class MaxValueMineHeap {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        boolean[][] visited = new boolean[m][n];
        int maxValue = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    int value = bfs(grid, visited, i, j, m, n);
                    maxValue = Math.max(maxValue, value);
                }
            }
        }

        System.out.println(maxValue);
    }

    private static int bfs(int[][] grid, boolean[][] visited, int x, int y, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int sum = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            sum += grid[pos[0]][pos[1]];

            for (int d = 0; d < 4; d++) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                    && !visited[nx][ny] && grid[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return sum;
    }
}
