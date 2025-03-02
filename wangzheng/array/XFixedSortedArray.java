package array;

import utils.PrintUtil;

// 实现一个大小固定的有序数组，支持动态增删改操作
public class XFixedSortedArray {

    public int[] data;

    public int cnt;

    public XFixedSortedArray(int capacity) {
        this.data = new int[capacity];
        this.cnt = 0;
    }

    public boolean insert(int val) {
        if (cnt == data.length) return false;
        if (cnt == 0) {
            data[cnt++] = val;
            return true;
        }
        for (int i = cnt; i > 0; i--) {
            if (data[i - 1] > val) {
                data[i] = data[i - 1];
            } else {
                data[i] = val;
                break;
            }
        }
        cnt++;
        return true;
    }

    public void delete(int index) {
        for (int i = index; i < cnt - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--cnt] = 0;
    }

    public void update(int index, int value) {
        data[index] = value;
    }

    public int find(int index) {
        if (index < 0 || index >= cnt) return -1;
        return data[index];
    }

    public static void main(String[] args) {
        XFixedSortedArray xFixedSortedArray = new XFixedSortedArray(10);
        xFixedSortedArray.insert(1);
        xFixedSortedArray.insert(3);
        xFixedSortedArray.insert(2);
        PrintUtil.printArray(xFixedSortedArray.data);
        xFixedSortedArray.delete(2);
        PrintUtil.printArray(xFixedSortedArray.data);
    }
}
