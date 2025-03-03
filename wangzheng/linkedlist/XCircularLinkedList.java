package linkedlist;

import utils.ListNode;
import utils.PrintUtil;

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

  public static void main(String[] args) {
    XCircularLinkedList xCircularLinkedList = new XCircularLinkedList();
    xCircularLinkedList.insertTail(1);
    xCircularLinkedList.insertTail(2);
    xCircularLinkedList.insertTail(3);
    xCircularLinkedList.delete(2);
  }
}
