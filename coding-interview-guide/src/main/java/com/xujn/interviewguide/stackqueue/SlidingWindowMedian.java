package com.xujn.interviewguide.stackqueue;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 滑动窗口中位数。
 */
public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || nums.length < k) {
            return new double[0];
        }
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
        TreeSet<Integer> left = new TreeSet<>(comparator);
        TreeSet<Integer> right = new TreeSet<>(comparator);
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addIndex(i, left, right);
            balance(left, right);
            if (i >= k) {
                removeIndex(i - k, left, right);
                balance(left, right);
            }
            if (i >= k - 1) {
                result[i - k + 1] = k % 2 == 0
                        ? ((long) nums[left.last()] + nums[right.first()]) / 2.0
                        : nums[left.last()];
            }
        }
        return result;
    }

    private void addIndex(int index, TreeSet<Integer> left, TreeSet<Integer> right) {
        if (left.isEmpty() || left.comparator().compare(index, left.last()) <= 0) {
            left.add(index);
        } else {
            right.add(index);
        }
    }

    private void removeIndex(int index, TreeSet<Integer> left, TreeSet<Integer> right) {
        if (!left.remove(index)) {
            right.remove(index);
        }
    }

    private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
        while (left.size() < right.size()) {
            left.add(right.pollFirst());
        }
        while (left.size() > right.size() + 1) {
            right.add(left.pollLast());
        }
    }
}
