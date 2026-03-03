package com.xujn.offer.recursiondp;

import com.xujn.offer.bit.NumberOfOneBits;
import com.xujn.offer.bit.AddWithoutArithmetic;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertEquals(55, fibonacciSequence.fib(10));
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
    }
}
