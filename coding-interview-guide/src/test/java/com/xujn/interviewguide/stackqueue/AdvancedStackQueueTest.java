package com.xujn.interviewguide.stackqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdvancedStackQueueTest {

    @Test
    void shouldComputeMaximalRectangle() {
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        assertEquals(6, new MaximalRectangle().maximalRectangle(matrix));
    }

    @Test
    void shouldCountVisibleMountainPairs() {
        assertEquals(7, new VisibleMountainPairs().countVisiblePairs(new int[]{3, 1, 2, 4, 5}));
        assertEquals(3, new VisibleMountainPairs().countVisiblePairs(new int[]{5, 5, 5}));
    }

    @Test
    void shouldBehaveAsCatDogQueue() {
        CatDogQueue queue = new CatDogQueue();
        queue.add(new CatDogQueue.Dog());
        queue.add(new CatDogQueue.Cat());
        queue.add(new CatDogQueue.Dog());

        assertTrue(queue.pollAll() instanceof CatDogQueue.Dog);
        assertTrue(queue.pollCat() instanceof CatDogQueue.Cat);
        assertFalse(queue.isDogEmpty());
        assertTrue(queue.pollDog() instanceof CatDogQueue.Dog);
        assertTrue(queue.isEmpty());
    }
}
