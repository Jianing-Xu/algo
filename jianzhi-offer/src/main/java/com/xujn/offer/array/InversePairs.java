package com.xujn.offer.array;

/**
 * 数组中的逆序对。
 */
public class InversePairs {

    public int count(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int[] helper = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, helper);
    }

    private int mergeSort(int[] nums, int left, int right, int[] helper) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid, helper);
        count += mergeSort(nums, mid + 1, right, helper);
        count += merge(nums, left, mid, right, helper);
        return count;
    }

    private int merge(int[] nums, int left, int mid, int right, int[] helper) {
        int i = mid;
        int j = right;
        int index = right;
        int count = 0;
        while (i >= left && j >= mid + 1) {
            if (nums[i] > nums[j]) {
                count += j - mid;
                helper[index--] = nums[i--];
            } else {
                helper[index--] = nums[j--];
            }
        }
        while (i >= left) {
            helper[index--] = nums[i--];
        }
        while (j >= mid + 1) {
            helper[index--] = nums[j--];
        }
        System.arraycopy(helper, left, nums, left, right - left + 1);
        return count;
    }
}
