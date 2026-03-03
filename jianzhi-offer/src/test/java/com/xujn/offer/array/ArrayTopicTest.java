package com.xujn.offer.array;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayTopicTest {

    @Test
    void shouldFindDuplicateNumber() {
        DuplicateNumberInArray solution = new DuplicateNumberInArray();
        assertEquals(2, solution.findDuplicateBySwap(new int[]{2, 3, 1, 0, 2, 5, 3}));
        assertEquals(3, solution.findDuplicateBySet(new int[]{1, 3, 4, 2, 3}));
    }

    @Test
    void shouldFindNumberInSortedMatrix() {
        FindInSortedMatrix solution = new FindInSortedMatrix();
        int[][] matrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16}
        };
        assertTrue(solution.findNumber(matrix, 8));
    }

    @Test
    void shouldReorderArrayByParity() {
        ReorderArrayByParity solution = new ReorderArrayByParity();
        int[] result = solution.reorder(new int[]{1, 2, 3, 4, 5});
        int split = 0;
        while (split < result.length && (result[split] & 1) == 1) {
            split++;
        }
        for (int i = split; i < result.length; i++) {
            assertEquals(0, result[i] & 1);
        }
    }

    @Test
    void shouldPrintMatrixInSpiralOrder() {
        SpiralMatrixPrinter solution = new SpiralMatrixPrinter();
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5),
                solution.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    void shouldSolveCommonArrayProblems() {
        MajorityElement majorityElement = new MajorityElement();
        LeastKNumbers leastKNumbers = new LeastKNumbers();
        MaxSubarraySum maxSubarraySum = new MaxSubarraySum();
        MinNumberByConcatenation minNumber = new MinNumberByConcatenation();
        MaxValueInGrid maxValueInGrid = new MaxValueInGrid();
        ConstructProductArray constructProductArray = new ConstructProductArray();
        IsStraight isStraight = new IsStraight();
        InversePairs inversePairs = new InversePairs();
        StockMaxProfit stockMaxProfit = new StockMaxProfit();
        TwoSumInSortedArray twoSumInSortedArray = new TwoSumInSortedArray();
        ContinuousSequenceSum continuousSequenceSum = new ContinuousSequenceSum();

        assertEquals(2, majorityElement.findMajority(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
        assertEquals(new HashSet<>(List.of(1, 2)), new HashSet<>(leastKNumbers.getLeastNumbers(new int[]{3, 2, 1}, 2)));
        assertEquals(6, maxSubarraySum.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals("3033459", minNumber.minNumber(new int[]{3, 30, 34, 5, 9}));
        assertEquals(12, maxValueInGrid.maxValue(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        assertArrayEquals(new int[]{120, 60, 40, 30, 24}, constructProductArray.construct(new int[]{1, 2, 3, 4, 5}));
        assertTrue(isStraight.isStraight(new int[]{0, 0, 1, 2, 5}));
        assertEquals(5, inversePairs.count(new int[]{7, 5, 6, 4}));
        assertEquals(11, stockMaxProfit.maxProfit(new int[]{9, 11, 8, 5, 7, 12, 16, 14}));
        assertArrayEquals(new int[]{2, 7}, twoSumInSortedArray.twoSum(new int[]{1, 2, 4, 7, 11, 15}, 9));
        assertArrayEquals(new int[][]{{2, 3, 4}, {4, 5}}, continuousSequenceSum.findContinuousSequence(9));
    }
}
