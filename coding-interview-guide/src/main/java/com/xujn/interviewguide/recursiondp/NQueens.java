package com.xujn.interviewguide.recursiondp;

/**
 * N 皇后问题。
 */
public class NQueens {

    public int countSolutions(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return place(0, record, n);
    }

    public int countSolutionsByBit(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0, 0);
    }

    private int place(int row, int[] record, int n) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(record, row, col)) {
                record[row] = col;
                count += place(row + 1, record, n);
            }
        }
        return count;
    }

    private boolean isValid(int[] record, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (record[i] == col || Math.abs(record[i] - col) == Math.abs(row - i)) {
                return false;
            }
        }
        return true;
    }

    private int process(int limit, int colLimit, int leftDiagLimit, int rightDiagLimit) {
        if (colLimit == limit) {
            return 1;
        }
        int positions = limit & (~(colLimit | leftDiagLimit | rightDiagLimit));
        int count = 0;
        while (positions != 0) {
            int mostRightOne = positions & -positions;
            positions -= mostRightOne;
            count += process(limit,
                    colLimit | mostRightOne,
                    (leftDiagLimit | mostRightOne) << 1,
                    (rightDiagLimit | mostRightOne) >>> 1);
        }
        return count;
    }
}
