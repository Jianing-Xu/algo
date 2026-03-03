package com.xujn.interviewguide.stackqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StackQueueTest {

    @Test
    void shouldTrackMinimumValue() {
        GetMinStack stack = new GetMinStack();
        stack.push(3);
        stack.push(4);
        stack.push(1);
        stack.push(2);

        assertEquals(1, stack.getMin());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.getMin());
        assertEquals(1, stack.pop());
        assertEquals(3, stack.getMin());
    }

    @Test
    void shouldUseTwoStacksAsQueue() {
        QueueByStacks queue = new QueueByStacks();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        queue.offer(4);
        assertEquals(3, queue.poll());
        assertEquals(4, queue.poll());
    }

    @Test
    void shouldReturnSlidingWindowMaximums() {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        assertArrayEquals(new int[]{5, 5, 5, 4, 6, 7},
                solution.getMaxWindow(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3));
    }
}
