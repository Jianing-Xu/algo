package com.xujn.interviewguide.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringTopicTest {

    @Test
    void shouldJudgeRotationWords() {
        IsRotation solution = new IsRotation();
        assertTrue(solution.isRotation("yunzuocheng", "zuochengyun"));
        assertFalse(solution.isRotation("abc", "acb"));
    }

    @Test
    void shouldFindLongestUniqueSubstringLength() {
        LongestUniqueSubstring solution = new LongestUniqueSubstring();
        assertEquals(3, solution.maxUniqueLength("abcabcbb"));
        assertEquals(1, solution.maxUniqueLength("bbbbb"));
        assertEquals(4, solution.maxUniqueLength("pwwkewx"));
    }
}
