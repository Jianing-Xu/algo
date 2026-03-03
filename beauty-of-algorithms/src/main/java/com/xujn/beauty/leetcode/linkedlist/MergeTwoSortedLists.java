package com.xujn.beauty.leetcode.linkedlist;

/**
 * LeetCode 21. 合并两个有序链表 (Merge Two Sorted Lists)
 * 难度：简单
 *
 * 【题目描述】
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 *
 * 【解题思路：虚拟头节点 + 双指针归并】
 * 创建一个哑节点 dummy 作为合并链表的起点（避免处理头节点的特殊情况）。
 * 双指针分别指向两个链表当前比较位置，谁小就接到结果链表后面。
 *
 * 时间复杂度：O(n+m)   空间复杂度：O(1)
 */
public class MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // 虚拟头节点，最终返回 dummy.next
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // 其中一个链表已经走完了，直接接上另一个的剩余部分
        curr.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
