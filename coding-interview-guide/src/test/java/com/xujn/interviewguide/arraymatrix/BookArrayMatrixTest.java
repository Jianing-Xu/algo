package com.xujn.interviewguide.arraymatrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookArrayMatrixTest {

    @Test
    void shouldComputeMaxSubmatrixSum() {
        int[][] matrix = {
                {-90, 48, 78},
                {64, -40, 64},
                {-81, -7, 66}
        };
        assertEquals(209, new MaxSubmatrixSum().maxSum(matrix));
    }

    @Test
    void shouldFindLongestSubarrayWithSumK() {
        assertEquals(4, new LongestSubarraySumEqualsK().maxLength(new int[]{1, -1, 5, -2, 3}, 3));
    }

    @Test
    void shouldReturnTopKElements() {
        assertArrayEquals(new int[]{9, 7, 5}, new TopKElements().topK(new int[]{3, 1, 5, 7, 2, 9}, 3));
    }

    @Test
    void shouldSelectKthByBfprt() {
        assertEquals(4, new BfprtSelector().minKth(new int[]{7, 1, 3, 5, 4, 2, 6}, 4));
    }
}
