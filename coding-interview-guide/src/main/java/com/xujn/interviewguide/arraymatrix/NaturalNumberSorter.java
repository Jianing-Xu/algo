package com.xujn.interviewguide.arraymatrix;

/**
 * 自然数数组排序，数组值范围为 1..n 且不重复。
 */
public class NaturalNumberSorter {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i + 1) {
                swap(arr, i, arr[i] - 1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
