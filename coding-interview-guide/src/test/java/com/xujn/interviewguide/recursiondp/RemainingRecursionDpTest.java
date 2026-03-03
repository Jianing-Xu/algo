package com.xujn.interviewguide.recursiondp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemainingRecursionDpTest {

    @Test
    void shouldJudgeStringInterleaving() {
        StringInterleaving solution = new StringInterleaving();
        assertTrue(solution.isInterleave("ab", "12", "a1b2"));
        assertFalse(solution.isInterleave("ab", "12", "ab21"));
    }

    @Test
    void shouldFindLongestCommonSubstring() {
        assertEquals("12345", new LongestCommonSubstring().longest("abc12345def", "zz12345yy"));
    }

    @Test
    void shouldComputeEditCost() {
        assertEquals(8, new EditCost().minCost("ab12cd3", "abcdf", 5, 3, 2));
    }
}
