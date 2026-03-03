package com.xujn.interviewguide.linkedlist;

import com.xujn.interviewguide.model.ListNode;

/**
 * 按给定 pivot 将链表划分为小于、等于、大于三个区域。
 */
public class LinkedListPartition {

    public ListNode partition(ListNode head, int pivot) {
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode bigHead = null;
        ListNode bigTail = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }

        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }
}
