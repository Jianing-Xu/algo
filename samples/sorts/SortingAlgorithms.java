package sorts;

import java.util.*;

/**
 * 排序算法集合
 * 包含各种经典排序算法的实现
 */
public class SortingAlgorithms {
    
    /**
     * 冒泡排序
     * 时间复杂度：O(n²)，空间复杂度：O(1)
     * 稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // 优化：检测是否发生交换
            
            // 每轮将最大元素"冒泡"到末尾
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            
            // 如果没有发生交换，说明数组已有序
            if (!swapped) {
                break;
            }
        }
    }
    
    /**
     * 选择排序
     * 时间复杂度：O(n²)，空间复杂度：O(1)
     * 不稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            // 找到未排序部分的最小元素
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // 将最小元素交换到已排序部分的末尾
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }
    
    /**
     * 插入排序
     * 时间复杂度：O(n²)，空间复杂度：O(1)
     * 稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // 将key插入到已排序部分的正确位置
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * 归并排序
     * 时间复杂度：O(n log n)，空间复杂度：O(n)
     * 稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1);
    }
    
    /**
     * 归并排序的递归辅助方法
     */
    private static void mergeSortHelper(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        
        // 分治：递归排序左右两部分
        mergeSortHelper(arr, temp, left, mid);
        mergeSortHelper(arr, temp, mid + 1, right);
        
        // 合并两个有序部分
        merge(arr, temp, left, mid, right);
    }
    
    /**
     * 合并两个有序数组部分
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 复制数据到临时数组
        System.arraycopy(arr, left, temp, left, right - left + 1);
        
        int i = left;      // 左部分指针
        int j = mid + 1;   // 右部分指针
        int k = left;      // 合并结果指针
        
        // 合并两个有序部分
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        
        // 复制剩余元素
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }
    
    /**
     * 快速排序
     * 平均时间复杂度：O(n log n)，最坏时间复杂度：O(n²)
     * 空间复杂度：O(log n)，不稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void quickSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSortHelper(arr, 0, arr.length - 1);
    }
    
    /**
     * 快速排序的递归辅助方法
     */
    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            // 分区操作，返回基准元素的正确位置
            int pivotIndex = partition(arr, low, high);
            
            // 递归排序基准元素左右两部分
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }
    
    /**
     * 分区操作（Lomuto分区方案）
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 选择最后一个元素作为基准
        int i = low - 1;       // 小于基准的元素的索引
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * 堆排序
     * 时间复杂度：O(n log n)，空间复杂度：O(1)
     * 不稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // 逐个提取堆顶元素
        for (int i = n - 1; i > 0; i--) {
            // 将堆顶元素移到数组末尾
            swap(arr, 0, i);
            
            // 重新调整堆
            heapify(arr, i, 0);
        }
    }
    
    /**
     * 调整堆，使其满足最大堆性质
     */
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;       // 假设父节点最大
        int left = 2 * i + 1;  // 左子节点
        int right = 2 * i + 2; // 右子节点
        
        // 找到父节点和子节点中的最大值
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // 如果最大值不是父节点，则交换并继续调整
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    /**
     * 计数排序
     * 时间复杂度：O(n + k)，空间复杂度：O(k)
     * 稳定排序算法，适用于范围较小的整数排序
     * 
     * @param arr 待排序数组
     * @param maxValue 数组中的最大值
     */
    public static void countingSort(int[] arr, int maxValue) {
        int[] count = new int[maxValue + 1];
        int[] output = new int[arr.length];
        
        // 统计每个元素的出现次数
        for (int value : arr) {
            count[value]++;
        }
        
        // 计算累积计数
        for (int i = 1; i <= maxValue; i++) {
            count[i] += count[i - 1];
        }
        
        // 从后往前构建输出数组（保证稳定性）
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        
        // 复制回原数组
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    
    /**
     * 基数排序
     * 时间复杂度：O(d * (n + k))，空间复杂度：O(n + k)
     * 稳定排序算法
     * 
     * @param arr 待排序数组
     */
    public static void radixSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        
        // 找到最大值，确定位数
        int max = Arrays.stream(arr).max().orElse(0);
        
        // 对每一位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }
    
    /**
     * 按指定位数进行计数排序
     */
    private static void countingSortByDigit(int[] arr, int exp) {
        int[] count = new int[10]; // 0-9十个数字
        int[] output = new int[arr.length];
        
        // 统计当前位数字的出现次数
        for (int value : arr) {
            count[(value / exp) % 10]++;
        }
        
        // 计算累积计数
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // 构建输出数组
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        
        // 复制回原数组
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    
    /**
     * 交换数组中两个元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 打印数组
     */
    public static void printArray(int[] arr, String name) {
        System.out.println(name + ": " + Arrays.toString(arr));
    }
    
    /**
     * 验证数组是否已排序
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 生成随机数组
     */
    public static int[] generateRandomArray(int size, int maxValue) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(maxValue + 1);
        }
        return arr;
    }
    
    /**
     * 测试排序算法性能
     */
    public static void testSortingPerformance(String algorithmName, 
                                            Runnable sortFunction, int[] arr) {
        long startTime = System.currentTimeMillis();
        sortFunction.run();
        long endTime = System.currentTimeMillis();
        
        System.out.println(algorithmName + " - 耗时: " + (endTime - startTime) + "ms, " +
                          "结果正确: " + isSorted(arr));
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 排序算法测试 ===\n");
        
        // 测试小数组
        System.out.println("1. 小数组排序测试:");
        int[] testArray = {64, 34, 25, 12, 22, 11, 90};
        
        // 冒泡排序
        int[] arr1 = testArray.clone();
        printArray(arr1, "原数组");
        bubbleSort(arr1);
        printArray(arr1, "冒泡排序");
        
        // 选择排序
        int[] arr2 = testArray.clone();
        selectionSort(arr2);
        printArray(arr2, "选择排序");
        
        // 插入排序
        int[] arr3 = testArray.clone();
        insertionSort(arr3);
        printArray(arr3, "插入排序");
        
        // 归并排序
        int[] arr4 = testArray.clone();
        mergeSort(arr4);
        printArray(arr4, "归并排序");
        
        // 快速排序
        int[] arr5 = testArray.clone();
        quickSort(arr5);
        printArray(arr5, "快速排序");
        
        // 堆排序
        int[] arr6 = testArray.clone();
        heapSort(arr6);
        printArray(arr6, "堆排序");
        
        // 计数排序
        int[] arr7 = testArray.clone();
        countingSort(arr7, 90);
        printArray(arr7, "计数排序");
        
        // 基数排序
        int[] arr8 = testArray.clone();
        radixSort(arr8);
        printArray(arr8, "基数排序");
        
        // 性能测试
        System.out.println("\n2. 性能测试 (10000个元素):");
        int size = 10000;
        int maxValue = 1000;
        
        int[] original = generateRandomArray(size, maxValue);
        
        testSortingPerformance("冒泡排序", () -> bubbleSort(original.clone()), original.clone());
        testSortingPerformance("选择排序", () -> selectionSort(original.clone()), original.clone());
        testSortingPerformance("插入排序", () -> insertionSort(original.clone()), original.clone());
        testSortingPerformance("归并排序", () -> mergeSort(original.clone()), original.clone());
        testSortingPerformance("快速排序", () -> quickSort(original.clone()), original.clone());
        testSortingPerformance("堆排序", () -> heapSort(original.clone()), original.clone());
        testSortingPerformance("计数排序", () -> countingSort(original.clone(), maxValue), original.clone());
        testSortingPerformance("基数排序", () -> radixSort(original.clone()), original.clone());
    }
}