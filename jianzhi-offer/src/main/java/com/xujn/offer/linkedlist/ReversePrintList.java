package com.xujn.offer.linkedlist;

import com.xujn.offer.model.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 从尾到头打印链表。
 */
public class ReversePrintList {

    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.value);
            current = current.next;
        }
        int[] result = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }
        return result;
    }

    public List<Integer> reversePrintAsList(ListNode head) {
        int[] values = reversePrint(head);
        List<Integer> result = new ArrayList<>(values.length);
        for (int value : values) {
            result.add(value);
        }
        return result;
    }
}
