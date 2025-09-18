package leetcode;

import java.util.*;

/**
 * LeetCode二叉树相关问题解答
 * 包含二叉树的前序遍历等经典二叉树题目
 */
public class TreeProblems {
    
    /**
     * 二叉树节点定义
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {}
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
    
    // ==================== Binary Tree Preorder Traversal（二叉树的前序遍历）====================
    
    /**
     * LeetCode 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的前序遍历
     * 
     * 解题思路：递归
     * 前序遍历：根 -> 左 -> 右
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 递归栈空间
     * 
     * @param root 根节点
     * @return 前序遍历结果
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    
    private static void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        result.add(node.val);           // 访问根节点
        preorderHelper(node.left, result);   // 遍历左子树
        preorderHelper(node.right, result);  // 遍历右子树
    }
    
    /**
     * 二叉树的前序遍历（迭代实现）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 栈空间
     * 
     * @param root 根节点
     * @return 前序遍历结果
     */
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            
            // 先压入右子树，再压入左子树（因为栈是后进先出）
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return result;
    }
    
    /**
     * 二叉树的前序遍历（Morris遍历）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 
     * @param root 根节点
     * @return 前序遍历结果
     */
    public static List<Integer> preorderTraversalMorris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        
        while (current != null) {
            if (current.left == null) {
                // 没有左子树，访问当前节点，然后移动到右子树
                result.add(current.val);
                current = current.right;
            } else {
                // 找到左子树的最右节点
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // 建立线索
                    result.add(current.val); // 前序遍历在这里访问
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // 恢复树结构
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        
        return result;
    }
    
    // ==================== 其他二叉树遍历 ====================
    
    /**
     * LeetCode 94. 二叉树的中序遍历
     * 
     * @param root 根节点
     * @return 中序遍历结果
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    
    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        inorderHelper(node.left, result);    // 遍历左子树
        result.add(node.val);               // 访问根节点
        inorderHelper(node.right, result);   // 遍历右子树
    }
    
    /**
     * 二叉树的中序遍历（迭代实现）
     * 
     * @param root 根节点
     * @return 中序遍历结果
     */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // 一直向左走到底
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // 处理栈顶节点
            current = stack.pop();
            result.add(current.val);
            
            // 转向右子树
            current = current.right;
        }
        
        return result;
    }
    
    /**
     * LeetCode 145. 二叉树的后序遍历
     * 
     * @param root 根节点
     * @return 后序遍历结果
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }
    
    private static void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        postorderHelper(node.left, result);   // 遍历左子树
        postorderHelper(node.right, result);  // 遍历右子树
        result.add(node.val);                // 访问根节点
    }
    
    /**
     * 二叉树的后序遍历（迭代实现）
     * 
     * @param root 根节点
     * @return 后序遍历结果
     */
    public static List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisited = null;
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode peekNode = stack.peek();
                
                // 如果右子树存在且未被访问过
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        
        return result;
    }
    
    /**
     * LeetCode 102. 二叉树的层序遍历
     * 
     * @param root 根节点
     * @return 层序遍历结果
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    // ==================== 二叉树的基本操作 ====================
    
    /**
     * LeetCode 104. 二叉树的最大深度
     * 
     * @param root 根节点
     * @return 最大深度
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    /**
     * LeetCode 111. 二叉树的最小深度
     * 
     * @param root 根节点
     * @return 最小深度
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        int minDepth = Integer.MAX_VALUE;
        
        if (root.left != null) {
            minDepth = Math.min(minDepth, minDepth(root.left));
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth, minDepth(root.right));
        }
        
        return minDepth + 1;
    }
    
    /**
     * LeetCode 110. 平衡二叉树
     * 
     * @param root 根节点
     * @return 是否为平衡二叉树
     */
    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    private static int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * LeetCode 100. 相同的树
     * 
     * @param p 树p
     * @param q 树q
     * @return 是否相同
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null || q == null) {
            return false;
        }
        
        return p.val == q.val && 
               isSameTree(p.left, q.left) && 
               isSameTree(p.right, q.right);
    }
    
    /**
     * LeetCode 101. 对称二叉树
     * 
     * @param root 根节点
     * @return 是否对称
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return isSymmetricHelper(root.left, root.right);
    }
    
    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        
        if (left == null || right == null) {
            return false;
        }
        
        return left.val == right.val && 
               isSymmetricHelper(left.left, right.right) && 
               isSymmetricHelper(left.right, right.left);
    }
    
    /**
     * LeetCode 226. 翻转二叉树
     * 
     * @param root 根节点
     * @return 翻转后的根节点
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // 交换左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // 递归翻转左右子树
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
    
    // ==================== 二叉搜索树相关 ====================
    
    /**
     * LeetCode 98. 验证二叉搜索树
     * 
     * @param root 根节点
     * @return 是否为有效的二叉搜索树
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        return isValidBSTHelper(node.left, min, node.val) && 
               isValidBSTHelper(node.right, node.val, max);
    }
    
    /**
     * LeetCode 700. 二叉搜索树中的搜索
     * 
     * @param root 根节点
     * @param val 目标值
     * @return 包含目标值的节点
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
    
    /**
     * LeetCode 701. 二叉搜索树中的插入操作
     * 
     * @param root 根节点
     * @param val 插入值
     * @return 插入后的根节点
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }
    
    // ==================== 辅助方法 ====================
    
    /**
     * 根据数组创建二叉树（层序遍历方式）
     * 
     * @param values 节点值数组，null表示空节点
     * @return 根节点
     */
    public static TreeNode createTreeFromArray(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            
            // 左子节点
            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            
            // 右子节点
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        
        return root;
    }
    
    /**
     * 将二叉树转换为数组表示（层序遍历）
     * 
     * @param root 根节点
     * @return 数组表示
     */
    public static List<Integer> treeToArray(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node != null) {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add(null);
            }
        }
        
        // 移除末尾的null
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        
        return result;
    }
    
    /**
     * 打印二叉树（可视化）
     * 
     * @param root 根节点
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        List<List<String>> lines = new ArrayList<>();
        List<TreeNode> level = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        
        level.add(root);
        int nn = 1;
        int widest = 0;
        
        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            
            for (TreeNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.val);
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();
                    
                    next.add(n.left);
                    next.add(n.right);
                    
                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }
            
            if (widest % 2 == 1) widest++;
            
            lines.add(line);
            
            List<TreeNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }
        
        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;
            
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┌' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);
                    
                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }
            
            // print line of numbers
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);
                
                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
            perpiece /= 2;
        }
    }
    
    /**
     * 生成随机二叉树
     * 
     * @param maxDepth 最大深度
     * @param probability 节点存在概率
     * @return 根节点
     */
    public static TreeNode generateRandomTree(int maxDepth, double probability) {
        Random random = new Random();
        return generateRandomTreeHelper(maxDepth, probability, random, 1);
    }
    
    private static TreeNode generateRandomTreeHelper(int maxDepth, double probability, Random random, int value) {
        if (maxDepth <= 0 || random.nextDouble() > probability) {
            return null;
        }
        
        TreeNode node = new TreeNode(value);
        node.left = generateRandomTreeHelper(maxDepth - 1, probability * 0.8, random, value * 2);
        node.right = generateRandomTreeHelper(maxDepth - 1, probability * 0.8, random, value * 2 + 1);
        
        return node;
    }
    
    /**
     * 测试方法
     */
    public static void main(String[] args) {
        System.out.println("=== LeetCode 二叉树问题测试 ===\n");
        
        // 测试1：创建测试树
        System.out.println("1. 创建测试树:");
        
        // 创建测试树: [3,9,20,null,null,15,7]
        Integer[] treeArray = {3, 9, 20, null, null, 15, 7};
        TreeNode root = createTreeFromArray(treeArray);
        
        System.out.println("测试树结构:");
        printTree(root);
        System.out.println();
        
        // 测试2：二叉树遍历
        System.out.println("2. 二叉树遍历测试:");
        
        System.out.println("前序遍历:");
        List<Integer> preorder1 = preorderTraversal(root);
        List<Integer> preorder2 = preorderTraversalIterative(root);
        List<Integer> preorder3 = preorderTraversalMorris(root);
        
        System.out.println("递归: " + preorder1);
        System.out.println("迭代: " + preorder2);
        System.out.println("Morris: " + preorder3);
        System.out.println("一致性: " + (preorder1.equals(preorder2) && preorder2.equals(preorder3)));
        
        System.out.println("\n中序遍历:");
        List<Integer> inorder1 = inorderTraversal(root);
        List<Integer> inorder2 = inorderTraversalIterative(root);
        
        System.out.println("递归: " + inorder1);
        System.out.println("迭代: " + inorder2);
        System.out.println("一致性: " + inorder1.equals(inorder2));
        
        System.out.println("\n后序遍历:");
        List<Integer> postorder1 = postorderTraversal(root);
        List<Integer> postorder2 = postorderTraversalIterative(root);
        
        System.out.println("递归: " + postorder1);
        System.out.println("迭代: " + postorder2);
        System.out.println("一致性: " + postorder1.equals(postorder2));
        
        System.out.println("\n层序遍历:");
        List<List<Integer>> levelOrder = levelOrder(root);
        System.out.println("结果: " + levelOrder);
        
        // 测试3：二叉树基本操作
        System.out.println("\n3. 二叉树基本操作测试:");
        
        System.out.println("最大深度: " + maxDepth(root));
        System.out.println("最小深度: " + minDepth(root));
        System.out.println("是否平衡: " + isBalanced(root));
        System.out.println("是否对称: " + isSymmetric(root));
        
        // 测试4：树的比较
        System.out.println("\n4. 树的比较测试:");
        
        TreeNode root2 = createTreeFromArray(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode root3 = createTreeFromArray(new Integer[]{3, 9, 20, null, null, 15, 8});
        
        System.out.println("相同的树:");
        System.out.println("tree1 == tree2: " + isSameTree(root, root2));
        System.out.println("tree1 == tree3: " + isSameTree(root, root3));
        
        // 测试对称树
        TreeNode symmetricTree = createTreeFromArray(new Integer[]{1, 2, 2, 3, 4, 4, 3});
        TreeNode asymmetricTree = createTreeFromArray(new Integer[]{1, 2, 2, null, 3, null, 3});
        
        System.out.println("\n对称性测试:");
        System.out.println("对称树: " + isSymmetric(symmetricTree));
        System.out.println("非对称树: " + isSymmetric(asymmetricTree));
        
        // 测试5：树的翻转
        System.out.println("\n5. 树的翻转测试:");
        
        TreeNode originalTree = createTreeFromArray(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        System.out.println("原始树:");
        printTree(originalTree);
        
        TreeNode invertedTree = invertTree(originalTree);
        System.out.println("翻转后:");
        printTree(invertedTree);
        
        // 测试6：二叉搜索树
        System.out.println("\n6. 二叉搜索树测试:");
        
        TreeNode bst = createTreeFromArray(new Integer[]{5, 3, 8, 2, 4, 7, 9});
        TreeNode notBst = createTreeFromArray(new Integer[]{5, 3, 8, 2, 6, 7, 9});
        
        System.out.println("BST验证:");
        System.out.println("有效BST: " + isValidBST(bst));
        System.out.println("无效BST: " + isValidBST(notBst));
        
        // BST搜索
        System.out.println("\nBST搜索:");
        TreeNode found = searchBST(bst, 4);
        TreeNode notFound = searchBST(bst, 10);
        
        System.out.println("搜索4: " + (found != null ? found.val : "null"));
        System.out.println("搜索10: " + (notFound != null ? notFound.val : "null"));
        
        // BST插入
        System.out.println("\nBST插入:");
        System.out.println("插入前:");
        printTree(bst);
        
        TreeNode newBst = insertIntoBST(bst, 6);
        System.out.println("插入6后:");
        printTree(newBst);
        
        // 测试7：性能测试
        System.out.println("\n7. 性能测试:");
        
        // 创建大型二叉树进行性能测试
        int[] depths = {10, 15, 20};
        
        for (int depth : depths) {
            TreeNode largeTree = generateRandomTree(depth, 0.8);
            int nodeCount = countNodes(largeTree);
            
            System.out.printf("深度=%d, 节点数=%d:%n", depth, nodeCount);
            
            // 前序遍历性能
            long startTime = System.nanoTime();
            List<Integer> result1 = preorderTraversal(largeTree);
            long time1 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            List<Integer> result2 = preorderTraversalIterative(largeTree);
            long time2 = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            List<Integer> result3 = preorderTraversalMorris(largeTree);
            long time3 = System.nanoTime() - startTime;
            
            System.out.printf("  前序遍历: 递归=%.2fμs, 迭代=%.2fμs, Morris=%.2fμs%n", 
                            time1 / 1000.0, time2 / 1000.0, time3 / 1000.0);
            System.out.printf("  结果一致性: %s%n", 
                            result1.equals(result2) && result2.equals(result3));
            
            // 深度计算性能
            startTime = System.nanoTime();
            int depth1 = maxDepth(largeTree);
            long depthTime = System.nanoTime() - startTime;
            
            System.out.printf("  深度计算: %d (%.2fμs)%n", depth1, depthTime / 1000.0);
            System.out.println();
        }
        
        // 测试8：边界情况
        System.out.println("8. 边界情况测试:");
        
        System.out.println("空树测试:");
        TreeNode emptyTree = null;
        
        System.out.println("前序遍历: " + preorderTraversal(emptyTree));
        System.out.println("最大深度: " + maxDepth(emptyTree));
        System.out.println("最小深度: " + minDepth(emptyTree));
        System.out.println("是否平衡: " + isBalanced(emptyTree));
        System.out.println("是否对称: " + isSymmetric(emptyTree));
        System.out.println("是否有效BST: " + isValidBST(emptyTree));
        
        System.out.println("\n单节点树测试:");
        TreeNode singleNode = new TreeNode(42);
        
        System.out.println("前序遍历: " + preorderTraversal(singleNode));
        System.out.println("最大深度: " + maxDepth(singleNode));
        System.out.println("最小深度: " + minDepth(singleNode));
        System.out.println("是否平衡: " + isBalanced(singleNode));
        System.out.println("是否对称: " + isSymmetric(singleNode));
        System.out.println("是否有效BST: " + isValidBST(singleNode));
        
        // 测试9：特殊树结构
        System.out.println("\n9. 特殊树结构测试:");
        
        // 完全二叉树
        TreeNode completeTree = createTreeFromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("完全二叉树:");
        System.out.println("是否平衡: " + isBalanced(completeTree));
        System.out.println("最大深度: " + maxDepth(completeTree));
        
        // 链状树（退化为链表）
        TreeNode chainTree = new TreeNode(1);
        TreeNode current = chainTree;
        for (int i = 2; i <= 5; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        
        System.out.println("\n链状树:");
        System.out.println("是否平衡: " + isBalanced(chainTree));
        System.out.println("最大深度: " + maxDepth(chainTree));
        System.out.println("最小深度: " + minDepth(chainTree));
        
        // 测试10：正确性验证
        System.out.println("\n10. 正确性验证:");
        
        // 生成多个随机树进行测试
        int correctCount = 0;
        int totalTests = 100;
        
        for (int i = 0; i < totalTests; i++) {
            TreeNode randomTree = generateRandomTree(8, 0.7);
            
            // 验证遍历结果一致性
            List<Integer> pre1 = preorderTraversal(randomTree);
            List<Integer> pre2 = preorderTraversalIterative(randomTree);
            List<Integer> pre3 = preorderTraversalMorris(randomTree);
            
            List<Integer> in1 = inorderTraversal(randomTree);
            List<Integer> in2 = inorderTraversalIterative(randomTree);
            
            List<Integer> post1 = postorderTraversal(randomTree);
            List<Integer> post2 = postorderTraversalIterative(randomTree);
            
            if (pre1.equals(pre2) && pre2.equals(pre3) && 
                in1.equals(in2) && post1.equals(post2)) {
                correctCount++;
            }
        }
        
        System.out.printf("遍历算法一致性: %d/%d 正确, 正确率: %.2f%%%n", 
                        correctCount, totalTests, 100.0 * correctCount / totalTests);
        
        // 验证树操作的正确性
        int operationCorrect = 0;
        int operationTotal = 100;
        
        for (int i = 0; i < operationTotal; i++) {
            TreeNode tree = generateRandomTree(6, 0.8);
            
            // 验证翻转操作
            TreeNode original = copyTree(tree);
            TreeNode inverted = invertTree(tree);
            TreeNode doubleInverted = invertTree(inverted);
            
            if (isSameTree(original, doubleInverted)) {
                operationCorrect++;
            }
        }
        
        System.out.printf("树操作正确性: %d/%d 正确, 正确率: %.2f%%%n", 
                        operationCorrect, operationTotal, 
                        100.0 * operationCorrect / operationTotal);
    }
    
    /**
     * 计算树的节点数
     * 
     * @param root 根节点
     * @return 节点数
     */
    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    /**
     * 复制二叉树
     * 
     * @param root 根节点
     * @return 复制的树
     */
    private static TreeNode copyTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = copyTree(root.left);
        newNode.right = copyTree(root.right);
        
        return newNode;
    }
}