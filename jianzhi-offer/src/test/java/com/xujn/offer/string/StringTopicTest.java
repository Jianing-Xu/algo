package com.xujn.offer.string;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringTopicTest {

    @Test
    void shouldReplaceSpaces() {
        ReplaceSpaces solution = new ReplaceSpaces();
        assertEquals("We%20are%20happy.", solution.replace("We are happy."));
    }

    @Test
    void shouldGenerateUniquePermutations() {
        PermutationGenerator solution = new PermutationGenerator();
        assertEquals(List.of("aab", "aba", "baa"), solution.permutation("aab"));
    }

    @Test
    void shouldCalculateLongestSubstringWithoutRepeat() {
        LongestSubstringWithoutRepeat solution = new LongestSubstringWithoutRepeat();
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void shouldFindPathInMatrix() {
        PathInMatrix solution = new PathInMatrix();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertTrue(solution.exist(board, "ABCCED"));
        assertFalse(solution.exist(board, "ABCB"));
    }

    @Test
    void shouldSolveOtherStringProblems() {
        FirstUniqueCharacter firstUniqueCharacter = new FirstUniqueCharacter();
        ReverseWords reverseWords = new ReverseWords();
        LeftRotateString leftRotateString = new LeftRotateString();
        RegexMatcher regexMatcher = new RegexMatcher();
        ValidNumberString validNumberString = new ValidNumberString();
        StringToInteger stringToInteger = new StringToInteger();

        assertEquals('b', firstUniqueCharacter.firstUniqChar("abaccdeff"));
        assertEquals("blue is sky the", reverseWords.reverseWords("  the sky is blue  "));
        assertEquals("cdefgab", leftRotateString.reverseLeftWords("abcdefg", 2));
        assertTrue(regexMatcher.isMatch("aaa", "a*a"));
        assertFalse(regexMatcher.isMatch("aa", "a"));
        assertTrue(validNumberString.isNumber(" -1E-16 "));
        assertFalse(validNumberString.isNumber("12e+5.4"));
        assertEquals(-42, stringToInteger.strToInt("   -42"));
    }
}
