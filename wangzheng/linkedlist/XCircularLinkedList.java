package linkedlist;

import utils.ListNode;

// 循环链表
public class XCircularLinkedList {

  ListNode head;
  ListNode tail;

  public XCircularLinkedList() {
    ListNode listNode = new ListNode(0);
    head = listNode;
    tail = listNode;
    tail.next = head;
  }

  public static void main(String[] args) {
    XCircularLinkedList list = new XCircularLinkedList();
    list.insertTail(1);
    list.insertTail(2);
    list.insertTail(3);
    list.delete(2);
  }

  public void insertTail(int value) {
    ListNode node = new ListNode(value);
    tail.next = node;
    node.next = head;
    tail = node;
  }

  public void delete(int value) {
    ListNode p = head;
    ListNode prev = null;
    while (p != null) {
      prev = p;
      p = p.next;
      if (p.val == value) {
        prev.next = p.next;
        break;
      }
    }
  }
}
