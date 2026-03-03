package com.xujn.offer.stackqueue;

import com.xujn.offer.searchsort.MinInRotatedArray;
import com.xujn.offer.searchsort.CountOccurrencesInSortedArray;
import com.xujn.offer.searchsort.MissingNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StackQueueTopicTest {

    @Test
    void shouldUseQueueAndMinStack() {
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        assertEquals(1, queue.deleteHead());
        assertEquals(2, queue.deleteHead());
        assertEquals(-1, queue.deleteHead());

        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(4);
        minStack.push(2);
        minStack.push(1);
        assertEquals(1, minStack.min());
        assertEquals(1, minStack.pop());
        assertEquals(2, minStack.min());
    }

    @Test
    void shouldValidateStackSequenceAndFindRotatedMinimum() {
        ValidateStackSequences validateStackSequences = new ValidateStackSequences();
        MinInRotatedArray minInRotatedArray = new MinInRotatedArray();
        MaxQueue maxQueue = new MaxQueue();
        CountOccurrencesInSortedArray countOccurrencesInSortedArray = new CountOccurrencesInSortedArray();
        MissingNumber missingNumber = new MissingNumber();
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();

        assertTrue(validateStackSequences.validate(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 5, 3, 2, 1}));
        assertFalse(validateStackSequences.validate(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 3, 5, 1, 2}));
        assertEquals(1, minInRotatedArray.minArray(new int[]{3, 4, 5, 1, 2}));
        maxQueue.pushBack(1);
        maxQueue.pushBack(3);
        maxQueue.pushBack(2);
        assertEquals(3, maxQueue.maxValue());
        assertEquals(1, maxQueue.popFront());
        assertEquals(3, maxQueue.maxValue());
        assertEquals(2, countOccurrencesInSortedArray.search(new int[]{5, 7, 7, 8, 8, 10}, 8));
        assertEquals(2, missingNumber.missingNumber(new int[]{0, 1, 3}));
        assertArrayEquals(new int[]{3, 3, 5, 5, 6, 7},
                slidingWindowMaximum.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }
}
