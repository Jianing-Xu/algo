package array;

/**
 * 有序数组合并工具类
 * 实现两个有序数组合并为一个有序数组
 */
public class ArrayMerger {
    
    /**
     * 合并两个有序数组
     * 使用双指针技术，时间复杂度O(m+n)，空间复杂度O(m+n)
     * 
     * @param arr1 第一个有序数组
     * @param arr2 第二个有序数组
     * @return 合并后的有序数组
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        if (arr1 == null) return arr2;
        if (arr2 == null) return arr1;
        
        int m = arr1.length;
        int n = arr2.length;
        int[] result = new int[m + n];
        
        int i = 0, j = 0, k = 0;
        
        // 双指针遍历两个数组
        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        
        // 处理剩余元素
        while (i < m) {
            result[k++] = arr1[i++];
        }
        
        while (j < n) {
            result[k++] = arr2[j++];
        }
        
        return result;
    }
    
    /**
     * 原地合并两个有序数组
     * 假设arr1有足够的空间容纳arr2的所有元素
     * 
     * @param arr1 第一个有序数组，有足够空间
     * @param m arr1中有效元素的个数
     * @param arr2 第二个有序数组
     * @param n arr2中元素的个数
     */
    public static void mergeInPlace(int[] arr1, int m, int[] arr2, int n) {
        int i = m - 1;      // arr1有效元素的最后一个索引
        int j = n - 1;      // arr2的最后一个索引
        int k = m + n - 1;  // 合并后数组的最后一个索引
        
        // 从后往前填充，避免覆盖未处理的元素
        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }
        
        // 如果arr2还有剩余元素，复制到arr1
        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }
        
        // 如果arr1还有剩余元素，它们已经在正确位置，无需移动
    }
    
    /**
     * 合并多个有序数组
     * 使用分治法，两两合并
     * 
     * @param arrays 多个有序数组
     * @return 合并后的有序数组
     */
    public static int[] mergeMultiple(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }
        
        if (arrays.length == 1) {
            return arrays[0];
        }
        
        // 分治合并
        return mergeMultipleHelper(arrays, 0, arrays.length - 1);
    }
    
    /**
     * 分治合并的辅助方法
     * 
     * @param arrays 数组集合
     * @param left 左边界
     * @param right 右边界
     * @return 合并结果
     */
    private static int[] mergeMultipleHelper(int[][] arrays, int left, int right) {
        if (left == right) {
            return arrays[left];
        }
        
        int mid = left + (right - left) / 2;
        int[] leftResult = mergeMultipleHelper(arrays, left, mid);
        int[] rightResult = mergeMultipleHelper(arrays, mid + 1, right);
        
        return merge(leftResult, rightResult);
    }
    
    /**
     * 打印数组
     * @param arr 要打印的数组
     * @param name 数组名称
     */
    public static void printArray(int[] arr, String name) {
        System.out.print(name + ": [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        // 测试两个有序数组合并
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        
        System.out.println("=== 两个有序数组合并测试 ===");
        printArray(arr1, "数组1");
        printArray(arr2, "数组2");
        
        int[] merged = merge(arr1, arr2);
        printArray(merged, "合并结果");
        
        // 测试原地合并
        System.out.println("\n=== 原地合并测试 ===");
        int[] arr3 = new int[10]; // 有足够空间
        System.arraycopy(arr1, 0, arr3, 0, arr1.length);
        
        printArray(arr3, "合并前");
        mergeInPlace(arr3, arr1.length, arr2, arr2.length);
        printArray(arr3, "原地合并结果");
        
        // 测试多个数组合并
        System.out.println("\n=== 多个有序数组合并测试 ===");
        int[][] multiArrays = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {10, 11, 12}
        };
        
        for (int i = 0; i < multiArrays.length; i++) {
            printArray(multiArrays[i], "数组" + (i + 1));
        }
        
        int[] multiMerged = mergeMultiple(multiArrays);
        printArray(multiMerged, "多数组合并结果");
    }
}