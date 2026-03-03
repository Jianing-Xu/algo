package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 环形山峰中能互相看见的山峰对数量。
 */
public class VisibleMountainPairs {

    public long countVisiblePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        if (arr.length == 2) {
            return 1;
        }
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        Deque<Record> stack = new ArrayDeque<>();
        stack.push(new Record(arr[maxIndex]));
        long result = 0;
        int index = nextIndex(maxIndex, arr.length);
        while (index != maxIndex) {
            int value = arr[index];
            while (!stack.isEmpty() && value > stack.peek().value) {
                int times = stack.pop().count;
                result += internalPairs(times) + times * 2L;
            }
            if (!stack.isEmpty() && value == stack.peek().value) {
                stack.peek().count++;
            } else {
                stack.push(new Record(value));
            }
            index = nextIndex(index, arr.length);
        }

        while (stack.size() > 2) {
            int times = stack.pop().count;
            result += internalPairs(times) + times * 2L;
        }
        if (stack.size() == 2) {
            int times = stack.pop().count;
            result += internalPairs(times);
            result += stack.peek().count == 1 ? times : times * 2L;
        }
        result += internalPairs(stack.pop().count);
        return result;
    }

    private int nextIndex(int index, int size) {
        return index < size - 1 ? index + 1 : 0;
    }

    private long internalPairs(int count) {
        return count == 1 ? 0 : (long) count * (count - 1) / 2;
    }

    private static class Record {
        private final int value;
        private int count;

        private Record(int value) {
            this.value = value;
            this.count = 1;
        }
    }
}
