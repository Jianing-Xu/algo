package heap;

/**
 * @author xujn
 * @date 2025-03-08
 */
public class XHeap {

  int[] arr;
  int size;
  int capacity;

  public XHeap(int capacity) {
    this.capacity = capacity;
    arr = new int[capacity];
    size = 0;
  }

  public void insert(int value) {
    if (size == capacity) {
      throw new RuntimeException("堆已满");
    }
    size++;
    // 自下往上堆化
    arr[size] = value;
    int i = size;
    while (i / 2 > 0 && arr[i] > arr[i / 2]) {
      swap(arr, i, i / 2);
    }
  }

  public void removeMax() {
    if (size == 0) {
      throw new RuntimeException("堆为空");
    }
  }

  // 自上往下堆化
  private void heapify(int[] arr, int size, int i) {

  }

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
