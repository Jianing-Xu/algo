package com.xujn.interviewguide.arraymatrix;

/**
 * 旋转有序数组中的最小值。
 */
public class RotatedArrayMin {

    public int min(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("empty array");
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] < arr[right]) {
                return arr[left];
            }
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else if (arr[mid] < arr[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return arr[left];
    }
}
