package com.algo.beauty.ch20_lru;

import java.util.HashMap;

/**
 * 基于哈希表和双向链表实现LRU缓存
 */
public class LRUBaseHashTable<K, V> {

    /**
     * 双向链表节点
     */
    static class DNode<K, V> {
        private K key;
        private V value;
        private DNode<K, V> prev;
        private DNode<K, V> next;

        public DNode() {}

        public DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<K, DNode<K, V>> table;

    // 虚拟头尾节点
    private DNode<K, V> headNode;
    private DNode<K, V> tailNode;
    
    // 缓存容量
    private Integer capacity;
    private Integer length;

    public LRUBaseHashTable(int capacity) {
        this.length = 0;
        this.capacity = capacity;
        this.table = new HashMap<>();

        headNode = new DNode<>();
        tailNode = new DNode<>();
        headNode.next = tailNode;
        tailNode.prev = headNode;
    }

    /**
     * 新增操作
     */
    public void add(K key, V value) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            DNode<K, V> newNode = new DNode<>(key, value);
            table.put(key, newNode);
            addNodeToHead(newNode);

            if (++length > capacity) {
                DNode<K, V> tail = popTail();
                table.remove(tail.key);
                length--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 获取数据
     */
    public V get(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 删除数据
     */
    public void remove(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        table.remove(key);
        length--;
    }

    /**
     * 把节点移到头部
     */
    private void moveToHead(DNode<K, V> node) {
        removeNode(node);
        addNodeToHead(node);
    }

    /**
     * 移除队尾节点
     */
    private DNode<K, V> popTail() {
        DNode<K, V> node = tailNode.prev;
        removeNode(node);
        return node;
    }

    /**
     * 移除某个节点
     */
    private void removeNode(DNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 把节点插入到头部虚拟节点之后
     */
    private void addNodeToHead(DNode<K, V> node) {
        node.prev = headNode;
        node.next = headNode.next;
        headNode.next.prev = node;
        headNode.next = node;
    }
}
