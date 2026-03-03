package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;

/**
 * 反转链表。
 */
public class ReverseLinkedList {

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
