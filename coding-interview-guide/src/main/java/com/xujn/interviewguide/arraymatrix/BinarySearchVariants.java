package com.xujn.interviewguide.arraymatrix;

/**
 * 常见边界二分。
 */
public class BinarySearchVariants {

    public int firstEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                if (arr[mid] == target) {
                    answer = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    public int lastEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= target) {
                if (arr[mid] == target) {
                    answer = mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    public int firstGreaterOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= target) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    public int lastLessOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] <= target) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
}
