package com.xujn.interviewguide.linkedlist;

import com.xujn.interviewguide.model.ListNode;

/**
 * 判断链表是否有环，并返回入环节点。
 */
public class LinkedListCycleDetector {

    public ListNode detectCycleEntry(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
