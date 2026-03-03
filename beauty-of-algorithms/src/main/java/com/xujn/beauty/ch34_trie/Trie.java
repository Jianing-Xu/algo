package com.xujn.beauty.ch34_trie;

/**
 * Trie 树（字典树、前缀树）
 * 
 * 【核心原理】
 * Trie 树是一种专门处理“字符串匹配”的数据结构，用来解决在一组字符串集合中快速查找某个字符串的问题。
 * 它的本质，就是利用字符串之间的【公共前缀】，将重复的前缀合并在一起。
 * 
 * 操作效率：
 * 构建 Trie 树需要把每个字符按个放入，时间复杂度和空间复杂度都与所有字符长度和相关。
 * 但是对于【查询】操作：如果我们要找一个长度为 K 的目标串，那么走完树的最坏时间复杂度仅仅是 O(K)。
 * 
 * 适用于：搜索引擎搜索框的自动下拉提示、敏感词过滤拦截等场景。
 */
public class Trie {
    private TrieNode root = new TrieNode('/'); // 根节点存储一个无意义的字符/
    
    /**
     * Trie 树节点的内部类定义
     * 假设我们的字符集只有 26 个小写英文字母。
     */
    public class TrieNode {
        public char data;   // 当前节点实际存放的字符数据
        public TrieNode[] children = new TrieNode[26]; // 长度26的数组充当子元素的拉链
        public boolean isEndingChar = false; // 红点标记：以此节点是否能够成为一个合法单词的结尾
        
        public TrieNode(char data) {
            this.data = data;
        }
    }

    /**
     * 1. 往 Trie 树中插入一个字符串
     * @param text 将要插入的字符串
     */
    public void insert(String text) {
        TrieNode p = root; // 从根出发
        for (int i = 0; i < text.length(); ++i) {
            // 将字符减掉 'a' 作为映射到0-25大小数组里的下标
            int index = text.charAt(i) - 'a'; 
            
            // 如果当前分支没有创建，我们需要为后续字符铺路建立新节点
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text.charAt(i));
                p.children[index] = newNode;
            }
            // 指针往下走一层
            p = p.children[index];
        }
        // 当这个字符串全部分配建好之后，在它最后一个字符的节点身上打上“此处是单词结尾”的烙印
        p.isEndingChar = true;
    }

    /**
     * 2. 在 Trie 树中查找一个字符串是否存在
     * @param pattern 要查找的字符串
     * @return 存在返回true，不存在返回false
     */
    public boolean find(String pattern) {
        TrieNode p = root; // 从根出发
        
        // 顺着节点一路往下对比
        for (int i = 0; i < pattern.length(); ++i) {
            int index = pattern.charAt(i) - 'a';
            
            // 半路发现路断了（说明连公共前缀都未能匹配完），肯定不存在
            if (p.children[index] == null) {
                return false; 
            }
            p = p.children[index];
        }
        
        // 当整个查找的串都走完了路径还没断：
        // 【注意】：不能直接返回 true！
        // 如果我们存入的是 "hello"，查找的是 "he"，这时候虽然都能找到路径，
        // 但 "e" 节点上的 isEndingChar 并不是 true，不应被当作单词。必须看它是不是一个正式单词结尾。
        return p.isEndingChar; 
    }
}
