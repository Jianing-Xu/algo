package queue;

import utils.PrintUtil;

// 实现一个循环队列
public class XCircularQueue {

    int[] data;
    int head;
    int tail;

    public XCircularQueue(int capacity) {
        data = new int[capacity];
        head = 0;
        tail = 0;
    }

    public boolean enqueue(int value) {
        if ((tail + 1) % data.length == head) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        return true;
    }

    public int dequeue() {
        if (head == tail) {
            return -1;
        }
        return data[head++];
    }

    public static void main(String[] args) {
        XCircularQueue queue = new XCircularQueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        PrintUtil.printArray(queue.data);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
