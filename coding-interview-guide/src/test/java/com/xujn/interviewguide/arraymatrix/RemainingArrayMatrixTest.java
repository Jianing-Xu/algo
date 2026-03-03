package com.xujn.interviewguide.arraymatrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemainingArrayMatrixTest {

    @Test
    void shouldFindMinInRotatedArray() {
        assertEquals(1, new RotatedArrayMin().min(new int[]{4, 5, 6, 7, 1, 2, 3}));
        assertEquals(0, new RotatedArrayMin().min(new int[]{2, 2, 2, 0, 1}));
    }

    @Test
    void shouldFindLocalMinimumIndex() {
        int[] arr = {9, 7, 3, 5, 8, 10};
        int index = new LocalMinimum().findIndex(arr);
        assertEquals(3, arr[index]);
    }

    @Test
    void shouldHandleBinarySearchVariants() {
        BinarySearchVariants solution = new BinarySearchVariants();
        int[] arr = {1, 2, 2, 2, 4, 5, 7};
        assertEquals(1, solution.firstEqual(arr, 2));
        assertEquals(3, solution.lastEqual(arr, 2));
        assertEquals(4, solution.firstGreaterOrEqual(arr, 3));
        assertEquals(5, solution.lastLessOrEqual(arr, 6));
    }
}
