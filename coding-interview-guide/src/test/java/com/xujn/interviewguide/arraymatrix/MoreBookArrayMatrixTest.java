package com.xujn.interviewguide.arraymatrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoreBookArrayMatrixTest {

    @Test
    void shouldSortNaturalNumberArray() {
        int[] arr = {8, 2, 1, 6, 9, 3, 7, 5, 4};
        new NaturalNumberSorter().sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
    }

    @Test
    void shouldAdjustOddEvenIndexParity() {
        int[] arr = {1, 8, 3, 2, 4, 5};
        new OddEvenAdjuster().adjust(arr);
        for (int i = 0; i < arr.length; i++) {
            assertEquals(i & 1, arr[i] & 1);
        }
    }

    @Test
    void shouldRecoverOneLisSequence() {
        int[] lis = new LisSequenceRecovery().recover(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7});
        assertArrayEquals(new int[]{1, 3, 4, 8, 9}, lis);
    }
}
