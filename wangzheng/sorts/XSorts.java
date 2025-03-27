package sorts;

import java.util.Arrays;
import utils.PrintUtil;

/**
 * @author xujn
 * @date 2025-02-26
 */
public class XSorts {

  public static void bubbleSort(int[] arr) {
    int n = arr.length;
    if (n <= 1) {
      return;
    }
    boolean flag = false;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          swap(arr, j, j + 1);
          flag = true;
        }
      }
      if (!flag) {
        break;
      }
    }
  }

  public static void selectSort(int[] arr) {
    int n = arr.length;
    if (n <= 1) {
      return;
    }
    // 从未排序区域选择出最小的，插入到已排序区间尾部
    for (int i = 0; i < n; i++) {
      int j = i;
      for (int k = i + 1; k < n; k++) {
        if (arr[j] > arr[k]) {
          j = k;
        }
      }
      swap(arr, i, j);
    }
  }

  public static void insertSort(int[] arr) {
    int n = arr.length;
    if (n <= 1) {
      return;
    }
    for (int i = 1; i < n; i++) {
      int value = arr[i];
      int j = i - 1;
      // 从后往前遍历，找到插入的位置，其余元素依次后移
      for (; j >= 0; j--) {
        if (arr[j] > value) {
          arr[j + 1] = arr[j];
        } else {
          break;
        }
      }
      arr[j + 1] = value;
    }
  }

  public static void mergeSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  private static void merge(int[] arr, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left;
    int j = mid + 1;
    int k = 0;
    while (i <= mid && j <= right) {
      if (arr[i] < arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    while (j <= right) {
      temp[k++] = arr[j++];
    }
    for (i = left; i <= right; i++) {
      arr[i] = temp[i - left];
    }
  }

  public static void quickSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int pivot = partition(arr, left, right);
    quickSort(arr, left, pivot - 1);
    quickSort(arr, pivot + 1, right);
  }

  private static int partition(int[] arr, int left, int right) {
    int pivot = arr[right];
    int i = left; // i表示pivot元素在数组中的位置，左边小于pivot的元素，右边大于pivot的元素
    for (int j = left; j < right; j++) {
      if (arr[j] < pivot) {
        swap(arr, i, j);
        i++;
      }
    }
    arr[right] = arr[i];
    arr[i] = pivot;
    return i;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static int findKthLargest(int[] arr, int k) {
      if (arr == null || arr.length == 0 || k < 0 || k > arr.length) {
          return -1;
      }
      return quickSortKth(arr, 0, arr.length - 1, k - 1);
  }

    public static int quickSortKth(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int pivot = partition(arr, left, right);
        if (k == pivot) {
            return arr[pivot];
        } else if (k < pivot){
            return quickSortKth(arr, left, pivot - 1, k);
        } else {
            return quickSortKth(arr, pivot + 1, right, k);
        }
    }

  public static void main(String[] args) {
    // 对数器 生成随机序列，通过一个正确的实现方法和目标方法进行对比
    int testTime = 500000;     // 测试次数
    int maxSize = 100;       // 最大测试容量：生成数组的最大容量
    int maxValue = 100;      // 最大测试数据：生成随机数组中的值（-100~100）
    boolean succeed = true;  // 是否对比成功
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);// 生成随机数组
      int[] arr2 = Arrays.copyOf(arr1, arr1.length);
      bubbleSort(arr1);
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
    bubbleSort(arr);
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
