package com.xujn.offer.recursiondp;

/**
 * 机器人的运动范围。
 */
public class MovingCount {

    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) {
            return 0;
        }
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    private int dfs(int row, int col, int m, int n, int k, boolean[][] visited) {
        if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
            return 0;
        }
        if (digitSum(row) + digitSum(col) > k) {
            return 0;
        }
        visited[row][col] = true;
        return 1 + dfs(row + 1, col, m, n, k, visited) + dfs(row, col + 1, m, n, k, visited);
    }

    private int digitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
