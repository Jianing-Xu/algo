package com.xujn.interviewguide.arraymatrix;

/**
 * BFPRT 选择第 k 小元素。
 */
public class BfprtSelector {

    public int minKth(int[] arr, int k) {
        if (arr == null || k < 1 || k > arr.length) {
            throw new IllegalArgumentException("invalid input");
        }
        int[] copy = arr.clone();
        return select(copy, 0, copy.length - 1, k - 1);
    }

    private int select(int[] arr, int left, int right, int index) {
        if (left == right) {
            return arr[left];
        }
        int pivot = medianOfMedians(arr, left, right);
        int[] range = partition(arr, left, right, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        }
        if (index < range[0]) {
            return select(arr, left, range[0] - 1, index);
        }
        return select(arr, range[1] + 1, right, index);
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int size = right - left + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] medians = new int[size / 5 + offset];
        for (int team = 0; team < medians.length; team++) {
            int teamLeft = left + team * 5;
            int teamRight = Math.min(right, teamLeft + 4);
            insertionSort(arr, teamLeft, teamRight);
            medians[team] = arr[(teamLeft + teamRight) / 2];
        }
        return medians.length == 1 ? medians[0] : select(medians, 0, medians.length - 1, medians.length / 2);
    }

    private int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;
        int more = right + 1;
        int current = left;
        while (current < more) {
            if (arr[current] < pivot) {
                swap(arr, ++less, current++);
            } else if (arr[current] > pivot) {
                swap(arr, --more, current);
            } else {
                current++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left && arr[j - 1] > arr[j]; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
