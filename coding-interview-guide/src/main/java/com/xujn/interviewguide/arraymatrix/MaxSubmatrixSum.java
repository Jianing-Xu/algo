package com.xujn.interviewguide.arraymatrix;

/**
 * 子矩阵最大累加和。
 */
public class MaxSubmatrixSum {

    public int maxSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int top = 0; top < rows; top++) {
            int[] sums = new int[cols];
            for (int bottom = top; bottom < rows; bottom++) {
                for (int col = 0; col < cols; col++) {
                    sums[col] += matrix[bottom][col];
                }
                max = Math.max(max, kadane(sums));
            }
        }
        return max;
    }

    private int kadane(int[] arr) {
        int current = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            current = Math.max(arr[i], current + arr[i]);
            max = Math.max(max, current);
        }
        return max;
    }
}
