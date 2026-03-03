package com.xujn.offer.string;

/**
 * 矩阵中的路径。
 */
public class PathInMatrix {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (dfs(board, word, row, col, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (visited[row][col] || board[row][col] != word.charAt(index)) {
            return false;
        }
        visited[row][col] = true;
        boolean found = dfs(board, word, row + 1, col, index + 1, visited)
                || dfs(board, word, row - 1, col, index + 1, visited)
                || dfs(board, word, row, col + 1, index + 1, visited)
                || dfs(board, word, row, col - 1, index + 1, visited);
        visited[row][col] = false;
        return found;
    }
}
