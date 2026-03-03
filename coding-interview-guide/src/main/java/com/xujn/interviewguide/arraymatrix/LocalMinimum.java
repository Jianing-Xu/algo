package com.xujn.interviewguide.arraymatrix;

/**
 * 找到一个局部最小位置。
 */
public class LocalMinimum {

    public int findIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        int n = arr.length;
        if (arr[n - 1] < arr[n - 2]) {
            return n - 1;
        }
        int left = 1;
        int right = n - 2;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
