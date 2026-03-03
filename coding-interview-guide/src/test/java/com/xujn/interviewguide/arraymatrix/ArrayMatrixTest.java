package com.xujn.interviewguide.arraymatrix;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayMatrixTest {

    @Test
    void shouldPrintMatrixInSpiralOrder() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                new SpiralOrderPrinter().spiralOrder(matrix));
    }

    @Test
    void shouldComputeMaxSubarraySum() {
        assertEquals(6, new SubarrayMaxSum().maxSum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
