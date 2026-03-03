package com.xujn.interviewguide.linkedlist;

import com.xujn.interviewguide.model.ListNode;
import com.xujn.interviewguide.model.RandomListNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class AdvancedLinkedListTest {

    @Test
    void shouldFindCycleEntry() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        assertSame(n2, new LinkedListCycleDetector().detectCycleEntry(n1));
    }

    @Test
    void shouldPartitionLinkedListByPivot() {
        ListNode head = buildList(9, 0, 4, 5, 1, 3, 2, 5);
        ListNode partitioned = new LinkedListPartition().partition(head, 3);
        assertEquals(List.of(0, 1, 2, 3, 9, 4, 5, 5), toList(partitioned));
    }

    @Test
    void shouldCopyRandomList() {
        RandomListNode n1 = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        n1.next = n2;
        n2.next = n3;
        n1.random = n3;
        n2.random = n1;
        n3.random = null;

        RandomListNode copied = new CopyRandomList().copy(n1);

        assertNotSame(n1, copied);
        assertEquals(1, copied.value);
        assertEquals(2, copied.next.value);
        assertEquals(3, copied.next.next.value);
        assertSame(copied.next.next, copied.random);
        assertSame(copied, copied.next.random);
        assertNull(copied.next.next.random);
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

    private List<Integer> toList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.value);
            head = head.next;
        }
        return result;
    }
}
