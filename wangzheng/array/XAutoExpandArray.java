package array;

import utils.PrintUtil;

// 实现一个支持动态扩容的数组
public class XAutoExpandArray {

  public int[] data;

  public int len;

  public int cnt;

  public XAutoExpandArray(int capacity) {
    this.data = new int[capacity];
    this.len = capacity;
    this.cnt = 0;
  }

  public static void main(String[] args) {
    XAutoExpandArray xAutoExpandArray = new XAutoExpandArray(2);
    for (int i = 1; i <= 10; i++) {
      xAutoExpandArray.insert(i);
    }
    PrintUtil.printArray(xAutoExpandArray.data);
  }

  public void insert(int value) {
    if (len == cnt) {
      resize();
    }
    this.data[this.cnt++] = value;
  }

  private void resize() {
    int[] newData = new int[this.len << 2];
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i];
    }
    data = newData;
    this.len = this.len << 2;
  }
}
