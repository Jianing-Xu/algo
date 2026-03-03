package com.xujn.beauty.leetcode.linkedlist;

/**
 * LeetCode 206. 反转链表 (Reverse Linked List)
 * 难度：简单
 *
 * 【题目描述】
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 【解题思路：迭代法 —— 三指针翻转】
 * 使用三个指针 prev, curr, next 在一次遍历中完成反转：
 * - curr 指向当前正在处理的节点
 * - prev 指向 curr 的前一个节点（翻转后 curr 的 next 要指向它）
 * - next 用来临时保存 curr.next，防止链表断裂
 * 
 * 每一步的操作：
 * 1. 先用 next 保存 curr.next（保命操作，不然断了就找不到了）
 * 2. 把 curr.next 指向 prev（核心：翻转箭头方向）
 * 3. prev 和 curr 各往前走一步
 * 
 * 时间复杂度：O(n)   空间复杂度：O(1)
 */
public class ReverseLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // 迭代解法
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; // 1. 先保存下一个节点
            curr.next = prev;          // 2. 翻转：当前节点指向前一个
            prev = curr;               // 3. prev 前进
            curr = next;               // 4. curr 前进
        }

        return prev; // 循环结束时 curr==null，prev 就是新的头
    }

    // 递归解法（同样经典，面试时可能被要求写两种）
    public ListNode reverseListRecursive(ListNode head) {
        // 递归终止条件：空链表或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }
        // 递归到最后一个节点，它就是新链表的头
        ListNode newHead = reverseListRecursive(head.next);
        // 关键：让下一个节点指回自己
        head.next.next = head;
        // 断开原来的正向链接，防止形成环
        head.next = null;

        return newHead;
    }
}
