package com.xujn.interviewguide.linkedlist;

import com.xujn.interviewguide.model.RandomListNode;

/**
 * 复制含随机指针的链表。
 */
public class CopyRandomList {

    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode current = head;
        while (current != null) {
            RandomListNode copy = new RandomListNode(current.value);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }

        current = head;
        while (current != null) {
            RandomListNode copy = current.next;
            copy.random = current.random == null ? null : current.random.next;
            current = copy.next;
        }

        current = head;
        RandomListNode copyHead = head.next;
        while (current != null) {
            RandomListNode copy = current.next;
            current.next = copy.next;
            copy.next = copy.next == null ? null : copy.next.next;
            current = current.next;
        }
        return copyHead;
    }
}
