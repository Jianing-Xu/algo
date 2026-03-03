package com.xujn.interviewguide.linkedlist;

import com.xujn.interviewguide.model.ListNode;

/**
 * 判断链表是否为回文结构。
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode rightHead = reverse(slow.next);
        ListNode rightCursor = rightHead;
        ListNode leftCursor = head;
        boolean result = true;
        while (rightCursor != null) {
            if (leftCursor.value != rightCursor.value) {
                result = false;
                break;
            }
            leftCursor = leftCursor.next;
            rightCursor = rightCursor.next;
        }
        slow.next = reverse(rightHead);
        return result;
    }

    private ListNode reverse(ListNode head) {
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
