package com.xujn.interviewguide.arraymatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 转圈打印矩阵。
 */
public class SpiralOrderPrinter {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            if (top < bottom) {
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
            }
            if (left < right) {
                for (int row = bottom - 1; row > top; row--) {
                    result.add(matrix[row][left]);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }
}
