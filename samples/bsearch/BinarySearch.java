package bsearch;

/**
 * 二分查找算法集合
 * 包含各种二分查找的变体实现
 */
public class BinarySearch {
    
    /**
     * 标准二分查找
     * 在有序数组中查找目标值
     * 时间复杂度：O(log n)，空间复杂度：O(1)
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免溢出
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // 未找到
    }
    
    /**
     * 递归版本的二分查找
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在返回-1
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursiveHelper(arr, target, 0, arr.length - 1);
    }
    
    /**
     * 递归二分查找的辅助方法
     */
    private static int binarySearchRecursiveHelper(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursiveHelper(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursiveHelper(arr, target, left, mid - 1);
        }
    }
    
    /**
     * 查找第一个等于目标值的元素位置
     * 适用于有重复元素的有序数组
     * 
     * @param arr 有序数组（可能有重复元素）
     * @param target 目标值
     * @return 第一个等于目标值的索引，如果不存在返回-1
     */
    public static int findFirst(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // 继续在左半部分查找
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找最后一个等于目标值的元素位置
     * 适用于有重复元素的有序数组
     * 
     * @param arr 有序数组（可能有重复元素）
     * @param target 目标值
     * @return 最后一个等于目标值的索引，如果不存在返回-1
     */
    public static int findLast(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // 继续在右半部分查找
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找第一个大于等于目标值的元素位置
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 第一个大于等于目标值的索引，如果不存在返回数组长度
     */
    public static int findFirstGreaterOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = arr.length; // 默认返回数组长度，表示所有元素都小于target
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] >= target) {
                result = mid;
                right = mid - 1; // 继续在左半部分查找更小的索引
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找第一个大于目标值的元素位置
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 第一个大于目标值的索引，如果不存在返回数组长度
     */
    public static int findFirstGreater(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = arr.length;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找最后一个小于等于目标值的元素位置
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 最后一个小于等于目标值的索引，如果不存在返回-1
     */
    public static int findLastLessOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] <= target) {
                result = mid;
                left = mid + 1; // 继续在右半部分查找更大的索引
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 查找最后一个小于目标值的元素位置
     * 
     * @param arr 有序数组
     * @param target 目标值
     * @return 最后一个小于目标值的索引，如果不存在返回-1
     */
    public static int findLastLess(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 在旋转排序数组中查找目标值
     * 数组原本是升序排列，但在某个点进行了旋转
     * 
     * @param arr 旋转排序数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在返回-1
     */
    public static int searchInRotatedArray(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            // 判断哪一半是有序的
            if (arr[left] <= arr[mid]) {
                // 左半部分有序
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右半部分有序
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * 查找旋转排序数组中的最小值
     * 
     * @param arr 旋转排序数组
     * @return 最小值
     */
    public static int findMinInRotatedArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > arr[right]) {
                // 最小值在右半部分
                left = mid + 1;
            } else {
                // 最小值在左半部分（包括mid）
                right = mid;
            }
        }
        
        return arr[left];
    }
    
    /**
     * 在二维矩阵中进行二分查找
     * 矩阵每行从左到右递增，每列从上到下递增
     * 
     * @param matrix 二维矩阵
     * @param target 目标值
     * @return 是否找到目标值
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // 从右上角开始搜索
        int row = 0;
        int col = cols - 1;
        
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--; // 当前值太大，向左移动
            } else {
                row++; // 当前值太小，向下移动
            }
        }
        
        return false;
    }
    
    /**
     * 计算平方根（整数部分）
     * 使用二分查找
     * 
     * @param x 非负整数
     * @return x的平方根的整数部分
     */
    public static int sqrt(int x) {
        if (x < 2) {
            return x;
        }
        
        int left = 1;
        int right = x / 2;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid; // 使用long避免溢出
            
            if (square == x) {
                return mid;
            } else if (square < x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * 打印数组
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
        System.out.println("=== 二分查找算法测试 ===\n");
        
        // 测试标准二分查找
        System.out.println("1. 标准二分查找测试:");
        int[] arr1 = {1, 3, 5, 7, 9, 11, 13, 15};
        printArray(arr1, "数组");
        
        int target = 7;
        int index = binarySearch(arr1, target);
        System.out.println("查找 " + target + ": 索引 = " + index);
        
        target = 6;
        index = binarySearch(arr1, target);
        System.out.println("查找 " + target + ": 索引 = " + index);
        
        // 测试有重复元素的查找
        System.out.println("\n2. 重复元素查找测试:");
        int[] arr2 = {1, 2, 2, 2, 3, 4, 4, 5};
        printArray(arr2, "数组");
        
        target = 2;
        System.out.println("查找 " + target + ":");
        System.out.println("  第一个位置: " + findFirst(arr2, target));
        System.out.println("  最后一个位置: " + findLast(arr2, target));
        
        target = 4;
        System.out.println("查找 " + target + ":");
        System.out.println("  第一个位置: " + findFirst(arr2, target));
        System.out.println("  最后一个位置: " + findLast(arr2, target));
        
        // 测试模糊查找
        System.out.println("\n3. 模糊查找测试:");
        int[] arr3 = {1, 3, 5, 7, 9};
        printArray(arr3, "数组");
        
        target = 6;
        System.out.println("目标值 " + target + ":");
        System.out.println("  第一个 >= " + target + " 的位置: " + findFirstGreaterOrEqual(arr3, target));
        System.out.println("  第一个 > " + target + " 的位置: " + findFirstGreater(arr3, target));
        System.out.println("  最后一个 <= " + target + " 的位置: " + findLastLessOrEqual(arr3, target));
        System.out.println("  最后一个 < " + target + " 的位置: " + findLastLess(arr3, target));
        
        // 测试旋转数组查找
        System.out.println("\n4. 旋转数组查找测试:");
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        printArray(rotated, "旋转数组");
        
        target = 0;
        index = searchInRotatedArray(rotated, target);
        System.out.println("查找 " + target + ": 索引 = " + index);
        
        int min = findMinInRotatedArray(rotated);
        System.out.println("最小值: " + min);
        
        // 测试二维矩阵查找
        System.out.println("\n5. 二维矩阵查找测试:");
        int[][] matrix = {
            {1,  4,  7,  11},
            {2,  5,  8,  12},
            {3,  6,  9,  16}
        };
        
        System.out.println("矩阵:");
        for (int[] row : matrix) {
            printArray(row, "");
        }
        
        target = 5;
        boolean found = searchMatrix(matrix, target);
        System.out.println("查找 " + target + ": " + (found ? "找到" : "未找到"));
        
        target = 13;
        found = searchMatrix(matrix, target);
        System.out.println("查找 " + target + ": " + (found ? "找到" : "未找到"));
        
        // 测试平方根
        System.out.println("\n6. 平方根计算测试:");
        int[] testNumbers = {4, 8, 15, 25, 100};
        for (int num : testNumbers) {
            int sqrtResult = sqrt(num);
            System.out.println("sqrt(" + num + ") = " + sqrtResult);
        }
    }
}