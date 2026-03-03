package com.xujn.interviewguide.verifier;

import com.xujn.interviewguide.arraymatrix.BfprtSelector;
import com.xujn.interviewguide.arraymatrix.LongestSubarraySumEqualsK;
import com.xujn.interviewguide.arraymatrix.MaxSubmatrixSum;
import com.xujn.interviewguide.stackqueue.SubarrayCountWithBoundedDiff;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BookComparatorTest {

    @Test
    void shouldVerifySubarrayCountWithBoundedDiff() {
        RandomCaseGenerator generator = new RandomCaseGenerator(11L);
        SubarrayCountWithBoundedDiff solution = new SubarrayCountWithBoundedDiff();
        AlgorithmComparator.verify(
                () -> new BoundedCase(generator.nextIntArray(8, -3, 6), generator.nextInt(0, 5)),
                sample -> bruteForceBoundedCount(sample.arr(), sample.num()),
                sample -> solution.count(sample.arr(), sample.num()),
                300
        );
    }

    @Test
    void shouldVerifyMaxSubmatrixSum() {
        RandomCaseGenerator generator = new RandomCaseGenerator(12L);
        MaxSubmatrixSum solution = new MaxSubmatrixSum();
        AlgorithmComparator.verify(
                () -> nextMatrix(generator, 4, 4, -5, 8),
                this::bruteForceMaxSubmatrix,
                solution::maxSum,
                200
        );
    }

    @Test
    void shouldVerifyLongestSubarraySumEqualsK() {
        RandomCaseGenerator generator = new RandomCaseGenerator(13L);
        LongestSubarraySumEqualsK solution = new LongestSubarraySumEqualsK();
        AlgorithmComparator.verify(
                () -> new BoundedCase(generator.nextIntArray(8, -3, 3), generator.nextInt(-4, 6)),
                sample -> bruteForceLongestSubarray(sample.arr(), sample.num()),
                sample -> solution.maxLength(sample.arr(), sample.num()),
                300
        );
    }

    @Test
    void shouldVerifyBfprtAgainstSort() {
        RandomCaseGenerator generator = new RandomCaseGenerator(14L);
        BfprtSelector solution = new BfprtSelector();
        AlgorithmComparator.verify(
                () -> {
                    int[] arr = generator.nextIntArray(8, -10, 10);
                    int[] sample = arr.length == 0 ? new int[]{0} : arr;
                    int k = generator.nextInt(1, sample.length);
                    return new BoundedCase(sample, k);
                },
                sample -> bruteForceMinKth(sample.arr(), sample.num()),
                sample -> solution.minKth(sample.arr(), sample.num()),
                300
        );
    }

    private int bruteForceBoundedCount(int[] arr, int num) {
        int count = 0;
        for (int left = 0; left < arr.length; left++) {
            int max = arr[left];
            int min = arr[left];
            for (int right = left; right < arr.length; right++) {
                max = Math.max(max, arr[right]);
                min = Math.min(min, arr[right]);
                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;
    }

    private int[][] nextMatrix(RandomCaseGenerator generator, int maxRows, int maxCols, int minValue, int maxValue) {
        int rows = generator.nextInt(1, maxRows);
        int cols = generator.nextInt(1, maxCols);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = generator.nextInt(minValue, maxValue);
            }
        }
        return matrix;
    }

    private int bruteForceMaxSubmatrix(int[][] matrix) {
        int best = Integer.MIN_VALUE;
        for (int top = 0; top < matrix.length; top++) {
            for (int left = 0; left < matrix[0].length; left++) {
                for (int bottom = top; bottom < matrix.length; bottom++) {
                    for (int right = left; right < matrix[0].length; right++) {
                        int sum = 0;
                        for (int row = top; row <= bottom; row++) {
                            for (int col = left; col <= right; col++) {
                                sum += matrix[row][col];
                            }
                        }
                        best = Math.max(best, sum);
                    }
                }
            }
        }
        return best;
    }

    private int bruteForceLongestSubarray(int[] arr, int k) {
        int best = 0;
        for (int left = 0; left < arr.length; left++) {
            int sum = 0;
            for (int right = left; right < arr.length; right++) {
                sum += arr[right];
                if (sum == k) {
                    best = Math.max(best, right - left + 1);
                }
            }
        }
        return best;
    }

    private int bruteForceMinKth(int[] arr, int k) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        return copy[k - 1];
    }

    private record BoundedCase(int[] arr, int num) {
    }
}
