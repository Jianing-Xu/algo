package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 只包含 1 的最大矩形面积。
 */
public class MaximalRectangle {

    public int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        for (int[] row : matrix) {
            for (int col = 0; col < cols; col++) {
                heights[col] = row[col] == 0 ? 0 : heights[col] + 1;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftLessIndex - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
