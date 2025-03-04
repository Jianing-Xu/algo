package linkedlist;

import utils.ListNode;
import utils.PrintUtil;

public class LinkedListAlgo {

  // 实现单链表反转
  public static ListNode reverse(ListNode list) {
    ListNode reversedList = new ListNode(0);
    while (list != null) {
      ListNode listNode = new ListNode(list.val);
      listNode.next = reversedList.next;
      reversedList.next = listNode;
      list = list.next;
    }
    return reversedList;
  }

  // 实现两个有序的链表合并为一个有序链表
  public static ListNode mergeSortedLists(ListNode list1, ListNode list2) {
    ListNode mergedList = new ListNode(0);
    ListNode p = mergedList;
    while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
        p.next = list1;
        list1 = list1.next;
      } else {
        p.next = list2;
        list2 = list2.next;
      }
      p = p.next;
    }
    if (list1 != null) {
      p.next = list1;
    }
    if (list2 != null) {
      p.next = list2;
    }
    return mergedList;
  }

  // 实现求链表的中间结点
  public static ListNode findMiddleNode(ListNode list) {
    if (list == null) {
      return new ListNode(0);
    }
    ListNode fast = list;
    ListNode slow = list;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  // 检测环
  public static boolean checkCircle(ListNode list) {
    if (list == null) {
      return false;
    }
    ListNode fast = list;
    ListNode slow = list;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }

  // 删除倒数第K个结点
  public static ListNode deleteLastKth(ListNode list, int k) {
    if (list == null) {
      return null;
    }
    ListNode fast = list;
    for (int i = 0; i < k; i++) {
      fast = fast.next;
    }
    ListNode slow = list;
    ListNode prev = slow;
    while (fast != null) {
      fast = fast.next;
      prev = slow;
      slow = slow.next;
    }
    prev.next = slow.next;
    return slow;
  }

  public static void main(String[] args) {
    ListNode l1 = ListNode.arrToLinkedList(new int[]{1, 3, 5, 7, 9});
    ListNode l2 = ListNode.arrToLinkedList(new int[]{2, 4, 6, 8, 10});

    PrintUtil.printLinkedList(l1);
    PrintUtil.printLinkedList(reverse(l1));

    PrintUtil.printLinkedList(mergeSortedLists(l1, l2));

    System.out.println(findMiddleNode(l1).val);

    ListNode circleList = new ListNode(1);
    circleList.next = new ListNode(2);
    circleList.next.next = circleList;
    System.out.println(checkCircle(circleList));

    System.out.println(deleteLastKth(l1, 2).val);
    PrintUtil.printLinkedList(l1);
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode soldier = new ListNode(0); // 利用哨兵结点简化实现难度 技巧三
    ListNode p = soldier;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }

    if (l1 != null) {
      p.next = l1;
    }
    if (l2 != null) {
      p.next = l2;
    }
    return soldier.next;
  }
}
