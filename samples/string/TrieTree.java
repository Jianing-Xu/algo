package string;

import java.util.*;

/**
 * Trie树（前缀树）实现
 * 支持a-z这26个英文字母的字符集
 */
public class TrieTree {
    
    /**
     * Trie树节点
     */
    private static class TrieNode {
        TrieNode[] children;    // 子节点数组，索引0-25对应a-z
        boolean isEndOfWord;    // 标记是否为单词结尾
        int count;              // 以该节点为结尾的单词数量
        
        TrieNode() {
            children = new TrieNode[26]; // a-z共26个字母
            isEndOfWord = false;
            count = 0;
        }
    }
    
    private TrieNode root;  // 根节点
    private int size;       // 树中单词的总数
    
    /**
     * 构造函数
     */
    public TrieTree() {
        root = new TrieNode();
        size = 0;
    }
    
    /**
     * 插入单词到Trie树中
     * 时间复杂度：O(m)，其中m是单词长度
     * 
     * @param word 要插入的单词（只包含小写字母a-z）
     */
    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        TrieNode current = root;
        
        // 遍历单词的每个字符
        for (char c : word.toCharArray()) {
            if (c < 'a' || c > 'z') {
                throw new IllegalArgumentException("单词只能包含小写字母a-z");
            }
            
            int index = c - 'a'; // 计算字符对应的索引
            
            // 如果对应的子节点不存在，创建新节点
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
        }
        
        // 标记单词结尾
        if (!current.isEndOfWord) {
            current.isEndOfWord = true;
            size++;
        }
        current.count++;
    }
    
    /**
     * 查找单词是否存在于Trie树中
     * 时间复杂度：O(m)，其中m是单词长度
     * 
     * @param word 要查找的单词
     * @return true如果单词存在，否则false
     */
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }
    
    /**
     * 查找是否存在以给定前缀开头的单词
     * 时间复杂度：O(m)，其中m是前缀长度
     * 
     * @param prefix 前缀
     * @return true如果存在以该前缀开头的单词，否则false
     */
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
    
    /**
     * 删除单词
     * 时间复杂度：O(m)，其中m是单词长度
     * 
     * @param word 要删除的单词
     * @return true如果删除成功，false如果单词不存在
     */
    public boolean delete(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        
        return deleteHelper(root, word, 0);
    }
    
    /**
     * 删除单词的递归辅助方法
     */
    private boolean deleteHelper(TrieNode node, String word, int index) {
        if (index == word.length()) {
            // 到达单词末尾
            if (!node.isEndOfWord) {
                return false; // 单词不存在
            }
            
            node.isEndOfWord = false;
            node.count = 0;
            size--;
            
            // 如果当前节点没有子节点，可以删除
            return !hasChildren(node);
        }
        
        char c = word.charAt(index);
        int charIndex = c - 'a';
        TrieNode child = node.children[charIndex];
        
        if (child == null) {
            return false; // 单词不存在
        }
        
        boolean shouldDeleteChild = deleteHelper(child, word, index + 1);
        
        if (shouldDeleteChild) {
            node.children[charIndex] = null;
            
            // 如果当前节点不是单词结尾且没有其他子节点，可以删除
            return !node.isEndOfWord && !hasChildren(node);
        }
        
        return false;
    }
    
    /**
     * 检查节点是否有子节点
     */
    private boolean hasChildren(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 查找节点的辅助方法
     */
    private TrieNode searchNode(String word) {
        if (word == null) {
            return null;
        }
        
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            if (c < 'a' || c > 'z') {
                return null;
            }
            
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        
        return current;
    }
    
    /**
     * 获取所有以指定前缀开头的单词
     * 
     * @param prefix 前缀
     * @return 所有以该前缀开头的单词列表
     */
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode prefixNode = searchNode(prefix);
        
        if (prefixNode != null) {
            collectWords(prefixNode, prefix, result);
        }
        
        return result;
    }
    
    /**
     * 收集所有单词的递归辅助方法
     */
    private void collectWords(TrieNode node, String currentWord, List<String> result) {
        if (node.isEndOfWord) {
            result.add(currentWord);
        }
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char nextChar = (char) ('a' + i);
                collectWords(node.children[i], currentWord + nextChar, result);
            }
        }
    }
    
    /**
     * 获取所有单词
     * 
     * @return 所有单词的列表
     */
    public List<String> getAllWords() {
        return getWordsWithPrefix("");
    }
    
    /**
     * 获取单词数量
     * 
     * @return 单词数量
     */
    public int size() {
        return size;
    }
    
    /**
     * 判断Trie树是否为空
     * 
     * @return true如果为空，否则false
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * 清空Trie树
     */
    public void clear() {
        root = new TrieNode();
        size = 0;
    }
    
    /**
     * 获取指定单词的出现次数
     * 
     * @param word 单词
     * @return 出现次数，如果单词不存在返回0
     */
    public int getCount(String word) {
        TrieNode node = searchNode(word);
        return (node != null && node.isEndOfWord) ? node.count : 0;
    }
    
    /**
     * 获取最长公共前缀
     * 
     * @param words 单词数组
     * @return 最长公共前缀
     */
    public static String longestCommonPrefix(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        TrieTree trie = new TrieTree();
        
        // 将所有单词插入Trie树
        for (String word : words) {
            if (word != null && !word.isEmpty()) {
                trie.insert(word);
            }
        }
        
        // 从根节点开始查找公共前缀
        StringBuilder prefix = new StringBuilder();
        TrieNode current = trie.root;
        
        while (true) {
            int childCount = 0;
            int nextIndex = -1;
            
            // 统计当前节点的子节点数量
            for (int i = 0; i < 26; i++) {
                if (current.children[i] != null) {
                    childCount++;
                    nextIndex = i;
                }
            }
            
            // 如果只有一个子节点且当前节点不是单词结尾，继续
            if (childCount == 1 && !current.isEndOfWord) {
                prefix.append((char) ('a' + nextIndex));
                current = current.children[nextIndex];
            } else {
                break;
            }
        }
        
        return prefix.toString();
    }
    
    /**
     * 获取Trie树的统计信息
     * 
     * @return 统计信息字符串
     */
    public String getStatistics() {
        int[] stats = new int[3]; // [节点数, 最大深度, 叶子节点数]
        calculateStats(root, 0, stats);
        
        return String.format(
            "Trie树统计信息:\n" +
            "  单词数量: %d\n" +
            "  节点数量: %d\n" +
            "  最大深度: %d\n" +
            "  叶子节点数: %d",
            size, stats[0], stats[1], stats[2]
        );
    }
    
    /**
     * 计算统计信息的递归辅助方法
     */
    private void calculateStats(TrieNode node, int depth, int[] stats) {
        stats[0]++; // 节点数量
        stats[1] = Math.max(stats[1], depth); // 最大深度
        
        boolean isLeaf = true;
        for (TrieNode child : node.children) {
            if (child != null) {
                isLeaf = false;
                calculateStats(child, depth + 1, stats);
            }
        }
        
        if (isLeaf) {
            stats[2]++; // 叶子节点数
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== Trie树测试 ===\n");
        
        TrieTree trie = new TrieTree();
        
        // 测试插入操作
        System.out.println("1. 插入单词测试:");
        String[] words = {"apple", "app", "application", "apply", "banana", "band", "bandana"};
        
        for (String word : words) {
            trie.insert(word);
            System.out.println("插入: " + word);
        }
        
        System.out.println("Trie树大小: " + trie.size());
        System.out.println(trie.getStatistics());
        
        // 测试查找操作
        System.out.println("\n2. 查找单词测试:");
        String[] searchWords = {"app", "apple", "application", "appl", "banana", "orange"};
        
        for (String word : searchWords) {
            boolean found = trie.search(word);
            System.out.println("查找 '" + word + "': " + (found ? "找到" : "未找到"));
        }
        
        // 测试前缀查找
        System.out.println("\n3. 前缀查找测试:");
        String[] prefixes = {"app", "ban", "apple", "xyz"};
        
        for (String prefix : prefixes) {
            boolean hasPrefix = trie.startsWith(prefix);
            System.out.println("前缀 '" + prefix + "': " + (hasPrefix ? "存在" : "不存在"));
        }
        
        // 测试获取指定前缀的所有单词
        System.out.println("\n4. 获取前缀单词测试:");
        for (String prefix : new String[]{"app", "ban"}) {
            List<String> wordsWithPrefix = trie.getWordsWithPrefix(prefix);
            System.out.println("前缀 '" + prefix + "' 的所有单词: " + wordsWithPrefix);
        }
        
        // 测试获取所有单词
        System.out.println("\n5. 获取所有单词:");
        List<String> allWords = trie.getAllWords();
        System.out.println("所有单词: " + allWords);
        
        // 测试重复插入
        System.out.println("\n6. 重复插入测试:");
        System.out.println("插入前 'apple' 的计数: " + trie.getCount("apple"));
        trie.insert("apple");
        trie.insert("apple");
        System.out.println("重复插入后 'apple' 的计数: " + trie.getCount("apple"));
        System.out.println("Trie树大小: " + trie.size());
        
        // 测试删除操作
        System.out.println("\n7. 删除单词测试:");
        String[] deleteWords = {"app", "application", "orange"};
        
        for (String word : deleteWords) {
            boolean deleted = trie.delete(word);
            System.out.println("删除 '" + word + "': " + (deleted ? "成功" : "失败（单词不存在）"));
        }
        
        System.out.println("删除后的所有单词: " + trie.getAllWords());
        System.out.println("Trie树大小: " + trie.size());
        
        // 测试最长公共前缀
        System.out.println("\n8. 最长公共前缀测试:");
        String[][] testCases = {
            {"flower", "flow", "flight"},
            {"dog", "racecar", "car"},
            {"apple", "app", "application"},
            {"test", "testing", "tester"}
        };
        
        for (String[] testCase : testCases) {
            String lcp = TrieTree.longestCommonPrefix(testCase);
            System.out.println("单词组 " + Arrays.toString(testCase) + " 的最长公共前缀: '" + lcp + "'");
        }
        
        // 性能测试
        System.out.println("\n9. 性能测试:");
        TrieTree perfTrie = new TrieTree();
        
        long startTime = System.currentTimeMillis();
        
        // 插入大量单词
        for (int i = 0; i < 10000; i++) {
            String word = "word" + i;
            perfTrie.insert(word);
        }
        
        // 查找测试
        int foundCount = 0;
        for (int i = 0; i < 10000; i++) {
            String word = "word" + (i % 5000); // 一半存在，一半不存在
            if (perfTrie.search(word)) {
                foundCount++;
            }
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("插入10000个单词并查找10000次耗时: " + (endTime - startTime) + "ms");
        System.out.println("找到的单词数: " + foundCount);
        System.out.println(perfTrie.getStatistics());
        
        // 测试清空操作
        System.out.println("\n10. 清空操作测试:");
        System.out.println("清空前大小: " + trie.size());
        trie.clear();
        System.out.println("清空后大小: " + trie.size());
        System.out.println("清空后所有单词: " + trie.getAllWords());
    }
}