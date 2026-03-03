package com.xujn.interviewguide.recursiondp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoreBookRecursionDpTest {

    @Test
    void shouldCountCoinWays() {
        assertEquals(6, new CoinWays().ways(new int[]{5, 10, 25, 1}, 15));
    }

    @Test
    void shouldComputeLcsLength() {
        assertEquals(4, new LongestCommonSubsequence().length("ABCBDAB", "BDCABA"));
    }

    @Test
    void shouldCountBooleanExpressionWays() {
        BooleanExpressionWays solution = new BooleanExpressionWays();
        assertEquals(2, solution.countWays("1^0|0|1", false));
        assertEquals(3, solution.countWays("1^0|0|1", true));
    }
}
