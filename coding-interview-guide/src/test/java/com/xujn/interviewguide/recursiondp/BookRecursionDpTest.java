package com.xujn.interviewguide.recursiondp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRecursionDpTest {

    @Test
    void shouldComputeBurstBalloons() {
        assertEquals(167, new BurstBalloons().maxCoins(new int[]{3, 1, 5, 8}));
    }

    @Test
    void shouldCountNQueens() {
        NQueens solution = new NQueens();
        assertEquals(2, solution.countSolutions(4));
        assertEquals(2, solution.countSolutionsByBit(4));
    }

    @Test
    void shouldComputeHanoiStateStep() {
        assertEquals(5, new HanoiStateStep().step(new int[]{1, 2, 3}));
        assertEquals(-1, new HanoiStateStep().step(new int[]{2, 1, 3}));
    }

    @Test
    void shouldComputeCardGameWinnerScore() {
        assertEquals(101, new CardGameWinner().winnerScore(new int[]{1, 2, 100, 4}));
    }
}
