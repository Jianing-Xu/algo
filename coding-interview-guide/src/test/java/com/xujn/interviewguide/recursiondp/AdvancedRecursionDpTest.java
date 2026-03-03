package com.xujn.interviewguide.recursiondp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedRecursionDpTest {

    @Test
    void shouldComputeMinCoins() {
        assertEquals(4, new MinCoins().minCoins(new int[]{5, 2, 3}, 20));
        assertEquals(-1, new MinCoins().minCoins(new int[]{5, 3}, 2));
    }

    @Test
    void shouldComputeLisLength() {
        assertEquals(5, new LongestIncreasingSubsequence().lengthOfLis(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7}));
    }

    @Test
    void shouldComputeEditDistance() {
        assertEquals(3, new EditDistance().minDistance("horse", "ros"));
        assertEquals(5, new EditDistance().minDistance("intention", "execution"));
    }
}
