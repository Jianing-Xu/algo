package com.xujn.beauty.ch28_heap;

/**
 * 堆以及堆排序操作
 * (以大顶堆为例)
 */
public class Heap {
    private int[] a; // 数组，从下标1开始存储数据
    private int n;   // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    // 插入元素
    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        // 自下往上堆化
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    // 删除堆顶元素（最大元素）
    public void removeMax() {
        if (count == 0) return; // 堆中没有数据
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    // 自上往下堆化
    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos; // 继续往下堆化
        }
    }

    /**
     * 堆排序：
     * 1. 建堆
     * 2. 排序
     */
    public static void sort(int[] a, int n) {
        buildHeap(a, n); // 第一步：建堆
        int k = n;
        while (k > 1) { // 第二步：排序
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    // 建堆：从最后一个非叶子节点开始，自上往下堆化
    private static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
