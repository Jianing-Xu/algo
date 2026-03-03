package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：查找单入口空闲区域
 *
 * 【题目描述】
 * 给定一个m*n的网格地图，'O'表示空闲区域，'X'表示障碍物。
 * 找到所有只有一个入口（即只有一个格子与边界相邻的连通空闲区域），
 * 输出最大的单入口空闲区域的面积。如果没有，输出 NULL。
 *
 * 【解题思路：BFS + 边界条件判断】
 * 1. 遍历所有空闲格子，BFS 找连通分量
 * 2. 对每个连通分量，统计与边界直接相邻的格子数
 * 3. 只有一个边界入口的连通分量才是合法的
 * 4. 输出最大面积
 */
public class SingleEntryFreeArea {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();

        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            String line = sc.nextLine().trim();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        boolean[][] visited = new boolean[m][n];
        int maxArea = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'O' && !visited[i][j]) {
                    // BFS 找连通分量
                    Queue<int[]> queue = new LinkedList<>();
                    List<int[]> cells = new ArrayList<>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;

                    int borderEntries = 0;
                    while (!queue.isEmpty()) {
                        int[] pos = queue.poll();
                        cells.add(pos);

                        // 判断此格子是否在边界上
                        if (pos[0] == 0 || pos[0] == m - 1 || pos[1] == 0 || pos[1] == n - 1) {
                            borderEntries++;
                        }

                        for (int d = 0; d < 4; d++) {
                            int nx = pos[0] + dx[d];
                            int ny = pos[1] + dy[d];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n
                                    && !visited[nx][ny] && grid[nx][ny] == 'O') {
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }

                    // 只有一个边界入口
                    if (borderEntries == 1) {
                        maxArea = Math.max(maxArea, cells.size());
                    }
                }
            }
        }

        System.out.println(maxArea == -1 ? "NULL" : maxArea);
    }
}
