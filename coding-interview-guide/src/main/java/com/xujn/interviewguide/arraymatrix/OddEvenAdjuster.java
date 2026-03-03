package com.xujn.interviewguide.arraymatrix;

/**
 * 调整数组使偶数下标为偶数，奇数下标为奇数。
 */
public class OddEvenAdjuster {

    public void adjust(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int even = 0;
        int odd = 1;
        while (even < arr.length && odd < arr.length) {
            while (even < arr.length && (arr[even] & 1) == 0) {
                even += 2;
            }
            while (odd < arr.length && (arr[odd] & 1) == 1) {
                odd += 2;
            }
            if (even < arr.length && odd < arr.length) {
                swap(arr, even, odd);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
