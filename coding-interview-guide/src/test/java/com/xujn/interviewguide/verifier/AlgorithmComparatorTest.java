package com.xujn.interviewguide.verifier;

import com.xujn.interviewguide.arraymatrix.SubarrayMaxSum;
import com.xujn.interviewguide.stackqueue.SlidingWindowMaximum;
import com.xujn.interviewguide.string.LongestUniqueSubstring;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;

class AlgorithmComparatorTest {

    @Test
    void shouldVerifySlidingWindowMaximumByComparator() {
        RandomCaseGenerator generator = new RandomCaseGenerator(1L);
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        AlgorithmComparator.verify(
                () -> {
                    int[] arr = generator.nextIntArray(8, -5, 9);
                    int windowSize = arr.length == 0 ? 1 : generator.nextInt(1, arr.length);
                    return new WindowCase(arr, windowSize);
                },
                sample -> Arrays.toString(bruteForceWindow(sample.arr(), sample.windowSize())),
                sample -> Arrays.toString(solution.getMaxWindow(sample.arr(), sample.windowSize())),
                300
        );
    }

    @Test
    void shouldVerifyLongestUniqueSubstringByComparator() {
        RandomCaseGenerator generator = new RandomCaseGenerator(2L);
        LongestUniqueSubstring solution = new LongestUniqueSubstring();
        AlgorithmComparator.verify(
                () -> generator.nextLowercaseString(8),
                this::bruteForceUniqueLength,
                solution::maxUniqueLength,
                300
        );
    }

    @Test
    void shouldVerifyMaxSubarraySumByComparator() {
        RandomCaseGenerator generator = new RandomCaseGenerator(3L);
        SubarrayMaxSum solution = new SubarrayMaxSum();
        AlgorithmComparator.verify(
                () -> {
                    int[] arr = generator.nextIntArray(8, -10, 10);
                    return arr.length == 0 ? new int[]{0} : arr;
                },
                this::bruteForceMaxSubarray,
                solution::maxSum,
                300
        );
    }

    private int[] bruteForceWindow(int[] arr, int windowSize) {
        if (arr.length < windowSize) {
            return new int[0];
        }
        int[] result = new int[arr.length - windowSize + 1];
        for (int i = 0; i <= arr.length - windowSize; i++) {
            int max = arr[i];
            for (int j = i + 1; j < i + windowSize; j++) {
                max = Math.max(max, arr[j]);
            }
            result[i] = max;
        }
        return result;
    }

    private int bruteForceUniqueLength(String str) {
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            boolean[] seen = new boolean[256];
            for (int j = i; j < str.length(); j++) {
                char current = str.charAt(j);
                if (seen[current]) {
                    break;
                }
                seen[current] = true;
                max = Math.max(max, j - i + 1);
            }
        }
        return max;
    }

    private int bruteForceMaxSubarray(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private record WindowCase(int[] arr, int windowSize) {
    }
}
