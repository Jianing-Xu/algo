package sorts;

import utils.PrintUtil;

/**
 * @author xujn
 * @date 2025-02-26
 */
public class XSorts {

  public void bubbleSort(int[] arr) {
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

  public void selectSort(int[] arr) {
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

  public void insertSort(int[] arr) {
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

  public void mergeSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  private void merge(int[] arr, int left, int mid, int right) {
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

  public void quickSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int pivot = partition(arr, left, right);
    quickSort(arr, left, pivot - 1);
    quickSort(arr, pivot + 1, right);
  }

  private int partition(int[] arr, int left, int right) {
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

  public void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{4, 5, 6, 1, 3, 2};
    XSorts xSorts = new XSorts();
    // xSorts.bubbleSort(arr);
    // xSorts.insertSort(arr);
    // xSorts.selectSort(arr);
    // xSorts.mergeSort(arr, 0, arr.length - 1);
    xSorts.quickSort(arr, 0, arr.length - 1);
    PrintUtil.printArray(arr);
  }
}
