package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;

/**
 * 删除链表的节点。
 */
public class DeleteNodeInList {

    public ListNode deleteNode(ListNode head, int value) {
        if (head == null) {
            return null;
        }
        if (head.value == value) {
            return head.next;
        }
        ListNode current = head;
        while (current.next != null && current.next.value != value) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
        return head;
    }
}
