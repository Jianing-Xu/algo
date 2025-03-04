package queue;

import utils.ListNode;
import utils.PrintUtil;

// 用链表实现一个链式队列
public class XLinkedQueue {

    ListNode head;
    ListNode tail;

    public XLinkedQueue() {
    }

    public boolean enqueue(int value) {
        ListNode node = new ListNode(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        return true;
    }

    public int dequeue() {
        if (head == null) {
            return -1;
        }
        int datum = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return datum;
    }


    public static void main(String[] args) {
        XLinkedQueue queue = new XLinkedQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        PrintUtil.printLinkedList(queue.head);
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        PrintUtil.printLinkedList(queue.head);
    }
}
