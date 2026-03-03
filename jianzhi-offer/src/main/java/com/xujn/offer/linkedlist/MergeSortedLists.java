package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;

/**
 * 合并两个排序的链表。
 */
public class MergeSortedLists {

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode left = l1;
        ListNode right = l2;
        while (left != null && right != null) {
            if (left.value <= right.value) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = left != null ? left : right;
        return dummy.next;
    }
}
