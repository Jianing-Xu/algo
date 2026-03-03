package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;

/**
 * 两个链表的第一个公共节点。
 */
public class FirstCommonNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
