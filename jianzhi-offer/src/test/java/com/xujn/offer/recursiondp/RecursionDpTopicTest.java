package com.xujn.offer.recursiondp;

import com.xujn.offer.bit.NumberOfOneBits;
import com.xujn.offer.bit.AddWithoutArithmetic;
import com.xujn.offer.bit.SingleNumberII;
import com.xujn.offer.bit.SingleNumbers;
import com.xujn.offer.searchsort.FindNthDigit;
import com.xujn.offer.searchsort.NumberEqualIndex;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecursionDpTopicTest {

    @Test
    void shouldSolveClassicRecursionAndDpProblems() {
        FibonacciSequence fibonacciSequence = new FibonacciSequence();
        CuttingRope cuttingRope = new CuttingRope();
        MovingCount movingCount = new MovingCount();
        PowerFunction powerFunction = new PowerFunction();
        PrintOneToMaxNDigits printer = new PrintOneToMaxNDigits();
        TranslateNumber translateNumber = new TranslateNumber();
        UglyNumber uglyNumber = new UglyNumber();
        NumberOfOneBits numberOfOneBits = new NumberOfOneBits();
        LastRemainingInCircle lastRemainingInCircle = new LastRemainingInCircle();
        SumOneToN sumOneToN = new SumOneToN();
        AddWithoutArithmetic addWithoutArithmetic = new AddWithoutArithmetic();
        NumWays numWays = new NumWays();
        DicesProbability dicesProbability = new DicesProbability();
        SingleNumbers singleNumbers = new SingleNumbers();
        SingleNumberII singleNumberII = new SingleNumberII();
        FindNthDigit findNthDigit = new FindNthDigit();
        NumberEqualIndex numberEqualIndex = new NumberEqualIndex();

        assertEquals(55, fibonacciSequence.fib(10));
        assertEquals(21, numWays.numWays(7));
        assertEquals(36, cuttingRope.maxProduct(10));
        assertEquals(6, movingCount.movingCount(3, 3, 2));
        assertEquals(0.125, powerFunction.power(2, -3));
        assertEquals(List.of("1", "2", "3", "4", "5"), printer.printNumbers(1).subList(0, 5));
        assertEquals(5, translateNumber.translateNum(12258));
        assertEquals(12, uglyNumber.nthUglyNumber(10));
        assertEquals(3, numberOfOneBits.hammingWeight(0b1011));
        assertEquals(3, lastRemainingInCircle.lastRemaining(5, 3));
        assertEquals(15, sumOneToN.sumNums(5));
        assertEquals(12, addWithoutArithmetic.add(5, 7));
        assertEquals(11, dicesProbability.dicesProbability(2).length);
        assertEquals(1.0 / 6.0, dicesProbability.dicesProbability(2)[5], 1e-9);
        int[] singles = singleNumbers.singleNumbers(new int[]{4, 1, 4, 6});
        Set<Integer> actual = new HashSet<>(List.of(singles[0], singles[1]));
        assertEquals(Set.of(1, 6), actual);
        assertEquals(4, singleNumberII.singleNumber(new int[]{3, 4, 3, 3}));
        assertEquals(1, findNthDigit.findNthDigit(10));
        assertEquals(3, numberEqualIndex.findNumberEqualIndex(new int[]{-3, -1, 1, 3, 5}));
    }
}
