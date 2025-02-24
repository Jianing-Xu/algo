package array;

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
        data[cnt++] = val;
        return true;
    }

    public void delete() {

    }

    public void update() {

    }

    public int find(int index) {
        if (index < 0 || index >= cnt) return -1;
        return data[index];
    }
}
