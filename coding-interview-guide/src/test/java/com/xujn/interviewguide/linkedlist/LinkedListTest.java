package com.xujn.interviewguide.linkedlist;

import com.xujn.interviewguide.model.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    @Test
    void shouldReverseLinkedList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;

        ReverseLinkedList solution = new ReverseLinkedList();
        ListNode head = solution.reverse(n1);

        assertSame(n3, head);
        assertSame(n2, head.next);
        assertSame(n1, head.next.next);
    }

    @Test
    void shouldJudgePalindromeListAndRestoreStructure() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        PalindromeLinkedList solution = new PalindromeLinkedList();

        assertTrue(solution.isPalindrome(n1));
        assertSame(n2, n1.next);
        assertSame(n3, n2.next);
        assertSame(n4, n3.next);
        assertTrue(n4.next == null);
    }

    @Test
    void shouldRejectNonPalindromeList() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;

        assertFalse(new PalindromeLinkedList().isPalindrome(n1));
    }
}
