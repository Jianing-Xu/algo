package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 求每个位置左边和右边最近的小于它的位置。
 */
public class NearLessElements {

    public int[][] nearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0][0];
        }
        int[][] result = new int[arr.length][2];
        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> indexes = stack.pop();
                int leftLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int index : indexes) {
                    result[index][0] = leftLess;
                    result[index][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> indexes = stack.pop();
            int leftLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int index : indexes) {
                result[index][0] = leftLess;
                result[index][1] = -1;
            }
        }
        return result;
    }
}
