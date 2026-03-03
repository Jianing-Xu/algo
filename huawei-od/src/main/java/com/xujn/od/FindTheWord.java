package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：找到它（二维矩阵搜索单词）
 *
 * 【题目描述】
 * 在一个字符矩阵中搜索给定单词，单词可以沿上下左右方向连续拼接。
 * 每个格子只能用一次。如果能找到返回该单词第一个字母的坐标，否则返回"N"。
 *
 * 【解题思路：DFS + 回溯】
 */
public class FindTheWord {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        sc.nextLine();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            String line = sc.nextLine().trim();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        String word = sc.nextLine().trim();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[m][n];
                    if (dfs(grid, visited, i, j, word, 0, m, n)) {
                        System.out.println(i + " " + j);
                        return;
                    }
                }
            }
        }
        System.out.println("N");
    }

    private static boolean dfs(char[][] grid, boolean[][] visited, int x, int y, String word, int idx, int m, int n) {
        if (idx == word.length()) return true;
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (visited[x][y] || grid[x][y] != word.charAt(idx)) return false;

        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            if (dfs(grid, visited, x + dx[d], y + dy[d], word, idx + 1, m, n)) return true;
        }
        visited[x][y] = false;
        return false;
    }
}
