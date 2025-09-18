package comparator;

import java.util.Arrays;

import sorts.SortingAlgorithms;
import utils.PrintUtil;

public class Comparator {

    public static void main(String[] args) {
        // 对数器 生成随机序列，通过一个正确的实现方法和目标方法进行对比
        int testTime = 500000;     // 测试次数
        int maxSize = 100;       // 最大测试容量：生成数组的最大容量
        int maxValue = 100;      // 最大测试数据：生成随机数组中的值（-100~100）
        boolean succeed = true;  // 是否对比成功
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);// 生成随机数组
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            SortingAlgorithms.bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                PrintUtil.printArray(arr1);
                break;
            }
        }
        System.out.println(succeed ? "True！" : "False!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        PrintUtil.printArray(arr);
        SortingAlgorithms.bubbleSort(arr);
        PrintUtil.printArray(arr);
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
