package array;

import utils.PrintUtil;

public class ArrayAlgo {

    // 实现两个有序数组合并为一个有序数组
    public static int[] mergeSortedArray(int[] arr1, int[] arr2) {
        int[] ret = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                ret[k++] = arr1[i++];
            } else {
                ret[k++] = arr2[j++];
            }
            // 剪枝
            if (i == arr1.length) {
                while (j < arr2.length) {
                    ret[k++] = arr2[j++];
                }
            }
            if (j == arr2.length) {
                while (i < arr1.length) {
                    ret[k++] = arr1[i++];
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 5, 7, 9};
        int[] arr2 = new int[]{2, 4, 6, 8, 10};
        int[] ret = mergeSortedArray(arr1, arr2);
        PrintUtil.printArray(ret);
    }
}
