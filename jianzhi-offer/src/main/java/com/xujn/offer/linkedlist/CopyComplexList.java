package com.xujn.offer.linkedlist;

import com.xujn.offer.model.RandomListNode;

/**
 * 复杂链表的复制。
 */
public class CopyComplexList {

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
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        current = head;
        RandomListNode copyHead = head.next;
        while (current != null) {
            RandomListNode copy = current.next;
            current.next = copy.next;
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            current = current.next;
        }
        return copyHead;
    }
}
