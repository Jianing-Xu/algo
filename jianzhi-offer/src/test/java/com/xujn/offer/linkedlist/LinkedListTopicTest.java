package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;
import com.xujn.offer.model.RandomListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class LinkedListTopicTest {

    @Test
    void shouldHandleBasicLinkedListProblems() {
        ReversePrintList reversePrintList = new ReversePrintList();
        DeleteNodeInList deleteNodeInList = new DeleteNodeInList();
        KthNodeFromEnd kthNodeFromEnd = new KthNodeFromEnd();
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        MergeSortedLists mergeSortedLists = new MergeSortedLists();

        ListNode head = buildList(1, 2, 3, 4);
        assertArrayEquals(new int[]{4, 3, 2, 1}, reversePrintList.reversePrint(head));
        assertEquals(3, kthNodeFromEnd.getKthFromEnd(head, 2).value);

        ListNode deleted = deleteNodeInList.deleteNode(buildList(4, 5, 1, 9), 5);
        assertArrayEquals(new int[]{4, 1, 9}, toArray(deleted));

        ListNode reversed = reverseLinkedList.reverse(buildList(1, 2, 3));
        assertArrayEquals(new int[]{3, 2, 1}, toArray(reversed));

        ListNode merged = mergeSortedLists.merge(buildList(1, 2, 4), buildList(1, 3, 4));
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 4}, toArray(merged));
    }

    @Test
    void shouldCopyComplexList() {
        CopyComplexList solution = new CopyComplexList();
        RandomListNode first = new RandomListNode(1);
        RandomListNode second = new RandomListNode(2);
        RandomListNode third = new RandomListNode(3);
        first.next = second;
        second.next = third;
        first.random = third;
        second.random = first;
        third.random = second;

        RandomListNode copied = solution.copy(first);
        assertNotSame(first, copied);
        assertEquals(1, copied.value);
        assertEquals(3, copied.random.value);
        assertEquals(1, copied.next.random.value);
    }

    @Test
    void shouldFindFirstCommonNode() {
        FirstCommonNode solution = new FirstCommonNode();
        ListNode common = buildList(8, 4, 5);
        ListNode headA = buildList(4, 1);
        tail(headA).next = common;
        ListNode headB = buildList(5, 6, 1);
        tail(headB).next = common;

        assertSame(common, solution.getIntersectionNode(headA, headB));
    }

    private ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    private int[] toArray(ListNode head) {
        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        int[] result = new int[size];
        current = head;
        int index = 0;
        while (current != null) {
            result[index++] = current.value;
            current = current.next;
        }
        return result;
    }

    private ListNode tail(ListNode head) {
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }
}
