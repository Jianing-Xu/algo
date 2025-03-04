package queue;

import utils.PrintUtil;

// 用数组实现一个顺序队列
public class XArrayQueue {

  int[] data;

  int head = 0;
  int tail = 0;

  public XArrayQueue(int capacity) {
    data = new int[capacity];
  }

  public static void main(String[] args) {
    XArrayQueue queue = new XArrayQueue(3);
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    PrintUtil.printArray(queue.data);
    queue.enqueue(4);
    System.out.println(queue.dequeue());
    PrintUtil.printArray(queue.data);
  }

  public boolean enqueue(int value) {
    if (tail == data.length) {
      return false;
    }
    data[tail++] = value;
    return true;
  }

  public int dequeue() {
    if (head == tail) {
      return -1;
    }
    int datum = data[head];
    data[head++] = 0;
    return datum;
  }
}
