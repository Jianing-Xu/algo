package com.xujn.interviewguide.recursiondp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursionDpTest {

    @Test
    void shouldComputeFibonacciAndClimbStairs() {
        FibonacciSeries solution = new FibonacciSeries();
        assertEquals(8, solution.fibonacci(6));
        assertEquals(8, solution.climbStairs(5));
    }

    @Test
    void shouldComputeMatrixMinPathSum() {
        int[][] matrix = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        assertEquals(12, new MinPathSum().minPathSum(matrix));
    }
}
