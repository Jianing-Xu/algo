package com.xujn.interviewguide.verifier;

import com.xujn.interviewguide.stackqueue.MaximalRectangle;
import com.xujn.interviewguide.stackqueue.VisibleMountainPairs;
import org.junit.jupiter.api.Test;

class MoreComparatorTest {

    @Test
    void shouldVerifyVisibleMountainPairsByComparator() {
        RandomCaseGenerator generator = new RandomCaseGenerator(4L);
        VisibleMountainPairs solution = new VisibleMountainPairs();
        AlgorithmComparator.verify(
                () -> {
                    int[] arr = generator.nextIntArray(7, 1, 6);
                    return arr.length < 2 ? new int[]{1, 1} : arr;
                },
                this::bruteForceVisiblePairs,
                solution::countVisiblePairs,
                300
        );
    }

    @Test
    void shouldVerifyMaximalRectangleByComparator() {
        RandomCaseGenerator generator = new RandomCaseGenerator(5L);
        MaximalRectangle solution = new MaximalRectangle();
        AlgorithmComparator.verify(
                () -> nextBinaryMatrix(generator, 4, 4),
                this::bruteForceMaxRectangle,
                solution::maximalRectangle,
                200
        );
    }

    private long bruteForceVisiblePairs(int[] arr) {
        int n = arr.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isVisible(arr, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isVisible(int[] arr, int i, int j) {
        int min = Math.min(arr[i], arr[j]);
        boolean clockwise = true;
        int index = (i + 1) % arr.length;
        while (index != j) {
            if (arr[index] > min) {
                clockwise = false;
                break;
            }
            index = (index + 1) % arr.length;
        }
        boolean counterClockwise = true;
        index = (j + 1) % arr.length;
        while (index != i) {
            if (arr[index] > min) {
                counterClockwise = false;
                break;
            }
            index = (index + 1) % arr.length;
        }
        return clockwise || counterClockwise;
    }

    private int[][] nextBinaryMatrix(RandomCaseGenerator generator, int maxRows, int maxCols) {
        int rows = generator.nextInt(1, maxRows);
        int cols = generator.nextInt(1, maxCols);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = generator.nextInt(0, 1);
            }
        }
        return matrix;
    }

    private int bruteForceMaxRectangle(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int best = 0;
        for (int top = 0; top < rows; top++) {
            for (int left = 0; left < cols; left++) {
                for (int bottom = top; bottom < rows; bottom++) {
                    for (int right = left; right < cols; right++) {
                        if (allOnes(matrix, top, left, bottom, right)) {
                            best = Math.max(best, (bottom - top + 1) * (right - left + 1));
                        }
                    }
                }
            }
        }
        return best;
    }

    private boolean allOnes(int[][] matrix, int top, int left, int bottom, int right) {
        for (int row = top; row <= bottom; row++) {
            for (int col = left; col <= right; col++) {
                if (matrix[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
