package com.xujn.beauty.leetcode.hashtable;

import java.util.HashMap;

/**
 * LeetCode 146. LRU 缓存 (LRU Cache)
 * 难度：中等
 *
 * 【解题思路：HashMap + 双向链表，所有操作 O(1)】
 * HashMap 提供 O(1) 查找，双向链表提供 O(1) 的头插和尾删。
 */
public class LRUCache {

    class DLinkedNode {
        int key, value;
        DLinkedNode prev, next;
        DLinkedNode() {}
        DLinkedNode(int k, int v) { key = k; value = v; }
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int size, capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                DLinkedNode t = removeTail();
                cache.remove(t.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
