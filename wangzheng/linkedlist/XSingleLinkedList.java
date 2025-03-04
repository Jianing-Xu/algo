package linkedlist;

import utils.ListNode;
import utils.PrintUtil;

// 单链表
public class XSingleLinkedList {

    public ListNode head;

    public XSingleLinkedList() {
        this.head = new ListNode(0);
    }

    public void insertToHead(int value) {
        ListNode node = new ListNode(value);
        node.next = head.next;
        head.next = node;
    }

    public void insertTail(int value) {
        // 可以增加尾节点优化
        ListNode node = new ListNode(value);
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }

    public void delete(int value) {
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == value) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
    }

    public ListNode findByValue(int value) {
        ListNode cur = head;
        while (cur.next != null && cur.next.val != value) {
            cur.next = cur.next.next;
        }
        return cur;
    }

    public static void main(String[] args) {
        XSingleLinkedList list = new XSingleLinkedList();
        list.insertToHead(1);
        list.insertToHead(2);
        list.insertToHead(3);
        PrintUtil.printLinkedList(list.head);
        list.delete(2);
        PrintUtil.printLinkedList(list.head);
    }
}
