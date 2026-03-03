package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;

/**
 * 链表中倒数第 k 个节点。
 */
public class KthNodeFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
