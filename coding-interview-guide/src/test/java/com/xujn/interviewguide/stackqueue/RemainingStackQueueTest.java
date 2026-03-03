package com.xujn.interviewguide.stackqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RemainingStackQueueTest {

    @Test
    void shouldFindNearLessElements() {
        int[][] result = new NearLessElements().nearLess(new int[]{3, 4, 1, 5, 6, 2, 7});
        assertArrayEquals(new int[]{-1, 2}, result[0]);
        assertArrayEquals(new int[]{0, 2}, result[1]);
        assertArrayEquals(new int[]{-1, -1}, result[2]);
        assertArrayEquals(new int[]{2, 5}, result[3]);
    }

    @Test
    void shouldComputeSlidingWindowMedian() {
        double[] result = new SlidingWindowMedian().medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        assertArrayEquals(new double[]{1, -1, -1, 3, 5, 6}, result);
    }
}
