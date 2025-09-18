package tree;

import java.util.*;

/**
 * 二叉查找树（二叉搜索树）实现
 * 支持插入、删除、查找操作
 */
public class BinarySearchTree<T extends Comparable<T>> {
    
    /**
     * 二叉树节点
     */
    private static class TreeNode<T> {
        T data;                 // 节点数据
        TreeNode<T> left;       // 左子节点
        TreeNode<T> right;      // 右子节点
        
        TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    private TreeNode<T> root;   // 根节点
    private int size;           // 树中节点数量
    
    /**
     * 构造函数
     */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    
    /**
     * 插入元素
     * 时间复杂度：平均O(log n)，最坏O(n)
     * 
     * @param data 要插入的数据
     */
    public void insert(T data) {
        root = insertRecursive(root, data);
    }
    
    /**
     * 递归插入的辅助方法
     */
    private TreeNode<T> insertRecursive(TreeNode<T> node, T data) {
        // 基础情况：找到插入位置
        if (node == null) {
            size++;
            return new TreeNode<>(data);
        }
        
        // 递归插入
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, data);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, data);
        }
        // 如果相等，不插入重复元素
        
        return node;
    }
    
    /**
     * 删除元素
     * 时间复杂度：平均O(log n)，最坏O(n)
     * 
     * @param data 要删除的数据
     * @return true如果删除成功，false如果元素不存在
     */
    public boolean delete(T data) {
        int originalSize = size;
        root = deleteRecursive(root, data);
        return size < originalSize;
    }
    
    /**
     * 递归删除的辅助方法
     */
    private TreeNode<T> deleteRecursive(TreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        
        int cmp = data.compareTo(node.data);
        
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, data);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, data);
        } else {
            // 找到要删除的节点
            size--;
            
            // 情况1：叶子节点
            if (node.left == null && node.right == null) {
                return null;
            }
            
            // 情况2：只有一个子节点
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            
            // 情况3：有两个子节点
            // 找到右子树中的最小节点（中序后继）
            TreeNode<T> successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteRecursive(node.right, successor.data);
            size++; // 补偿上面的size--，因为实际删除的是successor
        }
        
        return node;
    }
    
    /**
     * 查找元素
     * 时间复杂度：平均O(log n)，最坏O(n)
     * 
     * @param data 要查找的数据
     * @return true如果找到，否则false
     */
    public boolean search(T data) {
        return searchRecursive(root, data) != null;
    }
    
    /**
     * 递归查找的辅助方法
     */
    private TreeNode<T> searchRecursive(TreeNode<T> node, T data) {
        if (node == null || data.compareTo(node.data) == 0) {
            return node;
        }
        
        if (data.compareTo(node.data) < 0) {
            return searchRecursive(node.left, data);
        } else {
            return searchRecursive(node.right, data);
        }
    }
    
    /**
     * 查找最小元素
     * 
     * @return 最小元素，如果树为空返回null
     */
    public T findMinValue() {
        if (root == null) {
            return null;
        }
        return findMin(root).data;
    }
    
    /**
     * 查找最小节点
     */
    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    /**
     * 查找最大元素
     * 
     * @return 最大元素，如果树为空返回null
     */
    public T findMaxValue() {
        if (root == null) {
            return null;
        }
        return findMax(root).data;
    }
    
    /**
     * 查找最大节点
     */
    private TreeNode<T> findMax(TreeNode<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    /**
     * 查找指定节点的前驱节点（中序遍历中的前一个节点）
     * 
     * @param data 节点数据
     * @return 前驱节点的数据，如果不存在返回null
     */
    public T findPredecessor(T data) {
        TreeNode<T> node = searchRecursive(root, data);
        if (node == null) {
            return null;
        }
        
        TreeNode<T> predecessor = findPredecessorNode(root, node, null);
        return predecessor != null ? predecessor.data : null;
    }
    
    /**
     * 查找前驱节点的辅助方法
     */
    private TreeNode<T> findPredecessorNode(TreeNode<T> root, TreeNode<T> target, TreeNode<T> predecessor) {
        if (root == null) {
            return predecessor;
        }
        
        if (root == target) {
            // 如果有左子树，前驱是左子树的最大节点
            if (root.left != null) {
                return findMax(root.left);
            }
            return predecessor;
        }
        
        if (target.data.compareTo(root.data) > 0) {
            // 目标在右子树，当前节点可能是前驱
            return findPredecessorNode(root.right, target, root);
        } else {
            // 目标在左子树
            return findPredecessorNode(root.left, target, predecessor);
        }
    }
    
    /**
     * 查找指定节点的后继节点（中序遍历中的后一个节点）
     * 
     * @param data 节点数据
     * @return 后继节点的数据，如果不存在返回null
     */
    public T findSuccessor(T data) {
        TreeNode<T> node = searchRecursive(root, data);
        if (node == null) {
            return null;
        }
        
        TreeNode<T> successor = findSuccessorNode(root, node, null);
        return successor != null ? successor.data : null;
    }
    
    /**
     * 查找后继节点的辅助方法
     */
    private TreeNode<T> findSuccessorNode(TreeNode<T> root, TreeNode<T> target, TreeNode<T> successor) {
        if (root == null) {
            return successor;
        }
        
        if (root == target) {
            // 如果有右子树，后继是右子树的最小节点
            if (root.right != null) {
                return findMin(root.right);
            }
            return successor;
        }
        
        if (target.data.compareTo(root.data) < 0) {
            // 目标在左子树，当前节点可能是后继
            return findSuccessorNode(root.left, target, root);
        } else {
            // 目标在右子树
            return findSuccessorNode(root.right, target, successor);
        }
    }
    
    /**
     * 获取树的大小
     * 
     * @return 节点数量
     */
    public int size() {
        return size;
    }
    
    /**
     * 判断树是否为空
     * 
     * @return true如果为空，否则false
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * 获取树的高度
     * 
     * @return 树的高度
     */
    public int height() {
        return heightRecursive(root);
    }
    
    /**
     * 递归计算高度的辅助方法
     */
    private int heightRecursive(TreeNode<T> node) {
        if (node == null) {
            return -1; // 空树高度为-1
        }
        
        int leftHeight = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * 前序遍历
     * 
     * @return 前序遍历结果列表
     */
    public List<T> preorderTraversal() {
        List<T> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }
    
    /**
     * 前序遍历的递归辅助方法
     */
    private void preorderRecursive(TreeNode<T> node, List<T> result) {
        if (node != null) {
            result.add(node.data);
            preorderRecursive(node.left, result);
            preorderRecursive(node.right, result);
        }
    }
    
    /**
     * 中序遍历
     * 
     * @return 中序遍历结果列表（有序）
     */
    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }
    
    /**
     * 中序遍历的递归辅助方法
     */
    private void inorderRecursive(TreeNode<T> node, List<T> result) {
        if (node != null) {
            inorderRecursive(node.left, result);
            result.add(node.data);
            inorderRecursive(node.right, result);
        }
    }
    
    /**
     * 后序遍历
     * 
     * @return 后序遍历结果列表
     */
    public List<T> postorderTraversal() {
        List<T> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }
    
    /**
     * 后序遍历的递归辅助方法
     */
    private void postorderRecursive(TreeNode<T> node, List<T> result) {
        if (node != null) {
            postorderRecursive(node.left, result);
            postorderRecursive(node.right, result);
            result.add(node.data);
        }
    }
    
    /**
     * 层序遍历（广度优先遍历）
     * 
     * @return 层序遍历结果列表
     */
    public List<T> levelOrderTraversal() {
        List<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            result.add(node.data);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    /**
     * 验证是否为有效的二叉搜索树
     * 
     * @return true如果是有效的BST，否则false
     */
    public boolean isValidBST() {
        return isValidBSTRecursive(root, null, null);
    }
    
    /**
     * 验证BST的递归辅助方法
     */
    private boolean isValidBSTRecursive(TreeNode<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        
        // 检查当前节点是否违反BST性质
        if ((min != null && node.data.compareTo(min) <= 0) ||
            (max != null && node.data.compareTo(max) >= 0)) {
            return false;
        }
        
        // 递归检查左右子树
        return isValidBSTRecursive(node.left, min, node.data) &&
               isValidBSTRecursive(node.right, node.data, max);
    }
    
    /**
     * 清空树
     */
    public void clear() {
        root = null;
        size = 0;
    }
    
    /**
     * 转换为字符串表示（中序遍历）
     * 
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return "BST" + inorderTraversal().toString();
    }
    
    /**
     * 打印树的结构
     */
    public void printTree() {
        printTreeRecursive(root, "", true);
    }
    
    /**
     * 打印树结构的递归辅助方法
     */
    private void printTreeRecursive(TreeNode<T> node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.data);
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printTreeRecursive(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printTreeRecursive(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== 二叉搜索树测试 ===\n");
        
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        
        // 测试插入操作
        System.out.println("1. 插入操作测试:");
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        
        for (int value : values) {
            bst.insert(value);
            System.out.println("插入 " + value + ", 树大小: " + bst.size());
        }
        
        System.out.println("\n树的结构:");
        bst.printTree();
        
        // 测试遍历操作
        System.out.println("\n2. 遍历操作测试:");
        System.out.println("前序遍历: " + bst.preorderTraversal());
        System.out.println("中序遍历: " + bst.inorderTraversal());
        System.out.println("后序遍历: " + bst.postorderTraversal());
        System.out.println("层序遍历: " + bst.levelOrderTraversal());
        
        // 测试查找操作
        System.out.println("\n3. 查找操作测试:");
        int[] searchValues = {40, 55, 80, 100};
        for (int value : searchValues) {
            boolean found = bst.search(value);
            System.out.println("查找 " + value + ": " + (found ? "找到" : "未找到"));
        }
        
        // 测试最值查找
        System.out.println("\n4. 最值查找测试:");
        System.out.println("最小值: " + bst.findMinValue());
        System.out.println("最大值: " + bst.findMaxValue());
        System.out.println("树高度: " + bst.height());
        
        // 测试前驱和后继
        System.out.println("\n5. 前驱后继测试:");
        int[] testNodes = {30, 50, 70, 10, 80};
        for (int node : testNodes) {
            Integer predecessor = bst.findPredecessor(node);
            Integer successor = bst.findSuccessor(node);
            System.out.println("节点 " + node + ": 前驱=" + predecessor + ", 后继=" + successor);
        }
        
        // 测试删除操作
        System.out.println("\n6. 删除操作测试:");
        int[] deleteValues = {10, 30, 50}; // 分别测试删除叶子节点、有一个子节点、有两个子节点
        
        for (int value : deleteValues) {
            System.out.println("\n删除 " + value + ":");
            System.out.println("删除前中序遍历: " + bst.inorderTraversal());
            boolean deleted = bst.delete(value);
            System.out.println("删除结果: " + (deleted ? "成功" : "失败"));
            System.out.println("删除后中序遍历: " + bst.inorderTraversal());
            System.out.println("树大小: " + bst.size());
        }
        
        System.out.println("\n删除后的树结构:");
        bst.printTree();
        
        // 测试BST有效性
        System.out.println("\n7. BST有效性测试:");
        System.out.println("是否为有效BST: " + bst.isValidBST());
        
        // 性能测试
        System.out.println("\n8. 性能测试:");
        BinarySearchTree<Integer> perfBST = new BinarySearchTree<>();
        
        long startTime = System.currentTimeMillis();
        
        // 插入10000个随机数
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            perfBST.insert(random.nextInt(50000));
        }
        
        // 查找5000次
        int foundCount = 0;
        for (int i = 0; i < 5000; i++) {
            if (perfBST.search(random.nextInt(50000))) {
                foundCount++;
            }
        }
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("插入10000个元素并查找5000次耗时: " + (endTime - startTime) + "ms");
        System.out.println("最终树大小: " + perfBST.size());
        System.out.println("树高度: " + perfBST.height());
        System.out.println("找到的元素数: " + foundCount);
        
        // 测试清空操作
        System.out.println("\n9. 清空操作测试:");
        System.out.println("清空前大小: " + bst.size());
        bst.clear();
        System.out.println("清空后大小: " + bst.size());
        System.out.println("清空后是否为空: " + bst.isEmpty());
    }
}