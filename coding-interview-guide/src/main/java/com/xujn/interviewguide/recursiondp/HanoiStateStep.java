package com.xujn.interviewguide.recursiondp;

/**
 * 汉诺塔状态对应的最优步骤数。
 */
public class HanoiStateStep {

    public int step(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 3, 2);
    }

    private int process(int[] arr, int index, int from, int to, int other) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] != from && arr[index] != to) {
            return -1;
        }
        if (arr[index] == from) {
            return process(arr, index - 1, from, other, to);
        }
        int rest = process(arr, index - 1, other, to, from);
        if (rest == -1) {
            return -1;
        }
        return (1 << index) + rest;
    }
}
