package com.xujn.beauty.leetcode.linkedlist;

/**
 * LeetCode 141. 环形链表 (Linked List Cycle)
 * 难度：简单
 *
 * 【题目描述】
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 【解题思路：快慢指针（Floyd 判圈算法）】
 * fast 每次走 2 步，slow 每次走 1 步。
 * - 如果链表有环：快指针迟早会「套圈」慢指针，它们一定会在环内相遇。
 * - 如果链表无环：快指针会先到达链表末尾（null）。
 * 
 * 时间复杂度：O(n)   空间复杂度：O(1)
 */
public class LinkedListCycle {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; this.next = null; }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next; // fast 先走一步，这样后面循环条件可以用 slow != fast

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false; // fast 到头了，说明无环
            }
            slow = slow.next;       // 慢指针走 1 步
            fast = fast.next.next;  // 快指针走 2 步
        }

        return true; // slow == fast，说明套圈了，有环！
    }
}
