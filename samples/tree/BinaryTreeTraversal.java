package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树遍历算法集合 包含递归和迭代版本的前序、中序、后序、层序遍历
 */
public class BinaryTreeTraversal {

  /**
   * 前序遍历 - 递归版本 访问顺序：根 -> 左 -> 右 时间复杂度：O(n)，空间复杂度：O(h)，其中h是树的高度
   *
   * @param root 根节点
   * @return 前序遍历结果
   */
  public static List<Integer> preorderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    preorderRecursiveHelper(root, result);
    return result;
  }

  // ==================== 前序遍历 ====================

  /**
   * 前序遍历递归辅助方法
   */
  private static void preorderRecursiveHelper(TreeNode node, List<Integer> result) {
    if (node != null) {
      result.add(node.val);                           // 访问根节点
      preorderRecursiveHelper(node.left, result);     // 遍历左子树
      preorderRecursiveHelper(node.right, result);    // 遍历右子树
    }
  }

  /**
   * 前序遍历 - 迭代版本 使用栈模拟递归过程 时间复杂度：O(n)，空间复杂度：O(h)
   *
   * @param root 根节点
   * @return 前序遍历结果
   */
  public static List<Integer> preorderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val);

      // 先压入右子节点，再压入左子节点（因为栈是后进先出）
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
   * 中序遍历 - 递归版本 访问顺序：左 -> 根 -> 右 对于二叉搜索树，中序遍历得到有序序列 时间复杂度：O(n)，空间复杂度：O(h)
   *
   * @param root 根节点
   * @return 中序遍历结果
   */
  public static List<Integer> inorderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    inorderRecursiveHelper(root, result);
    return result;
  }

  // ==================== 中序遍历 ====================

  /**
   * 中序遍历递归辅助方法
   */
  private static void inorderRecursiveHelper(TreeNode node, List<Integer> result) {
    if (node != null) {
      inorderRecursiveHelper(node.left, result);      // 遍历左子树
      result.add(node.val);                           // 访问根节点
      inorderRecursiveHelper(node.right, result);     // 遍历右子树
    }
  }

  /**
   * 中序遍历 - 迭代版本 使用栈模拟递归过程 时间复杂度：O(n)，空间复杂度：O(h)
   *
   * @param root 根节点
   * @return 中序遍历结果
   */
  public static List<Integer> inorderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;

    while (current != null || !stack.isEmpty()) {
      // 一直向左走到底，将路径上的节点入栈
      while (current != null) {
        stack.push(current);
        current = current.left;
      }

      // 弹出栈顶节点并访问
      current = stack.pop();
      result.add(current.val);

      // 转向右子树
      current = current.right;
    }

    return result;
  }

  /**
   * 后序遍历 - 递归版本 访问顺序：左 -> 右 -> 根 时间复杂度：O(n)，空间复杂度：O(h)
   *
   * @param root 根节点
   * @return 后序遍历结果
   */
  public static List<Integer> postorderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    postorderRecursiveHelper(root, result);
    return result;
  }

  // ==================== 后序遍历 ====================

  /**
   * 后序遍历递归辅助方法
   */
  private static void postorderRecursiveHelper(TreeNode node, List<Integer> result) {
    if (node != null) {
      postorderRecursiveHelper(node.left, result);    // 遍历左子树
      postorderRecursiveHelper(node.right, result);   // 遍历右子树
      result.add(node.val);                           // 访问根节点
    }
  }

  /**
   * 后序遍历 - 迭代版本 使用两个栈或者一个栈加标记的方法 时间复杂度：O(n)，空间复杂度：O(h)
   *
   * @param root 根节点
   * @return 后序遍历结果
   */
  public static List<Integer> postorderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();

    stack1.push(root);

    // 第一个栈用于遍历，第二个栈用于存储后序遍历的逆序
    while (!stack1.isEmpty()) {
      TreeNode node = stack1.pop();
      stack2.push(node);

      // 先压入左子节点，再压入右子节点
      if (node.left != null) {
        stack1.push(node.left);
      }
      if (node.right != null) {
        stack1.push(node.right);
      }
    }

    // 弹出第二个栈得到后序遍历结果
    while (!stack2.isEmpty()) {
      result.add(stack2.pop().val);
    }

    return result;
  }

  /**
   * 后序遍历 - 单栈迭代版本 使用一个栈和访问标记
   *
   * @param root 根节点
   * @return 后序遍历结果
   */
  public static List<Integer> postorderIterativeSingleStack(TreeNode root) {
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
          // 访问当前节点
          result.add(peekNode.val);
          lastVisited = stack.pop();
        }
      }
    }

    return result;
  }

  /**
   * 层序遍历（广度优先遍历） 使用队列实现，按层从左到右访问节点 时间复杂度：O(n)，空间复杂度：O(w)，其中w是树的最大宽度
   *
   * @param root 根节点
   * @return 层序遍历结果
   */
  public static List<Integer> levelOrder(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      result.add(node.val);

      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }
    }

    return result;
  }

  // ==================== 层序遍历 ====================

  /**
   * 分层的层序遍历 返回每一层的节点值
   *
   * @param root 根节点
   * @return 分层的遍历结果
   */
  public static List<List<Integer>> levelOrderByLevels(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<Integer> currentLevel = new ArrayList<>();

      // 处理当前层的所有节点
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

  /**
   * 锯齿形层序遍历 奇数层从左到右，偶数层从右到左
   *
   * @param root 根节点
   * @return 锯齿形遍历结果
   */
  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean leftToRight = true;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<Integer> currentLevel = new ArrayList<>();

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();

        if (leftToRight) {
          currentLevel.add(node.val);
        } else {
          currentLevel.add(0, node.val); // 插入到开头
        }

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      result.add(currentLevel);
      leftToRight = !leftToRight; // 切换方向
    }

    return result;
  }

  /**
   * 创建测试用的二叉树
   *
   * @return 二叉树根节点
   */
  public static TreeNode createTestTree() {
    /*
     * 创建如下结构的二叉树：
     *       1
     *      / \
     *     2   3
     *    / \   \
     *   4   5   6
     *  /
     * 7
     */
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.right = new TreeNode(6);
    root.left.left.left = new TreeNode(7);

    return root;
  }

  // ==================== 辅助方法 ====================

  /**
   * 打印二叉树结构
   *
   * @param root 根节点
   */
  public static void printTree(TreeNode root) {
    printTreeHelper(root, "", true);
  }

  /**
   * 打印树结构的辅助方法
   */
  private static void printTreeHelper(TreeNode node, String prefix, boolean isLast) {
    if (node != null) {
      System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);

      if (node.left != null || node.right != null) {
        if (node.left != null) {
          printTreeHelper(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
        }
        if (node.right != null) {
          printTreeHelper(node.right, prefix + (isLast ? "    " : "│   "), true);
        }
      }
    }
  }

  /**
   * 比较两种遍历方法的性能
   *
   * @param methodName        方法名称
   * @param root              根节点
   * @param traversalFunction 遍历函数
   */
  public static void performanceTest(String methodName, TreeNode root,
      java.util.function.Function<TreeNode, List<Integer>> traversalFunction) {
    long startTime = System.nanoTime();
    List<Integer> result = traversalFunction.apply(root);
    long endTime = System.nanoTime();

    double timeMs = (endTime - startTime) / 1_000_000.0;
    System.out.printf("%s: 节点数=%d, 耗时=%.3fms%n", methodName, result.size(), timeMs);
  }

  /**
   * 创建大型测试树
   *
   * @param depth 树的深度
   * @return 根节点
   */
  public static TreeNode createLargeTree(int depth) {
    if (depth <= 0) {
      return null;
    }

    TreeNode root = new TreeNode(1);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    int nodeValue = 2;
    int currentDepth = 1;

    while (!queue.isEmpty() && currentDepth < depth) {
      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();

        node.left = new TreeNode(nodeValue++);
        node.right = new TreeNode(nodeValue++);

        queue.offer(node.left);
        queue.offer(node.right);
      }

      currentDepth++;
    }

    return root;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 二叉树遍历算法测试 ===\n");

    // 创建测试树
    TreeNode root = createTestTree();

    System.out.println("测试二叉树结构:");
    printTree(root);

    // 测试各种遍历算法
    System.out.println("\n1. 前序遍历测试:");
    List<Integer> preorderRec = preorderRecursive(root);
    List<Integer> preorderIter = preorderIterative(root);
    System.out.println("递归版本: " + preorderRec);
    System.out.println("迭代版本: " + preorderIter);
    System.out.println("结果一致: " + preorderRec.equals(preorderIter));

    System.out.println("\n2. 中序遍历测试:");
    List<Integer> inorderRec = inorderRecursive(root);
    List<Integer> inorderIter = inorderIterative(root);
    System.out.println("递归版本: " + inorderRec);
    System.out.println("迭代版本: " + inorderIter);
    System.out.println("结果一致: " + inorderRec.equals(inorderIter));

    System.out.println("\n3. 后序遍历测试:");
    List<Integer> postorderRec = postorderRecursive(root);
    List<Integer> postorderIter = postorderIterative(root);
    List<Integer> postorderSingle = postorderIterativeSingleStack(root);
    System.out.println("递归版本: " + postorderRec);
    System.out.println("双栈迭代: " + postorderIter);
    System.out.println("单栈迭代: " + postorderSingle);
    System.out.println("结果一致: " + (postorderRec.equals(postorderIter) &&
        postorderIter.equals(postorderSingle)));

    System.out.println("\n4. 层序遍历测试:");
    List<Integer> levelOrderResult = levelOrder(root);
    System.out.println("层序遍历: " + levelOrderResult);

    List<List<Integer>> levelsByLevels = levelOrderByLevels(root);
    System.out.println("分层遍历: " + levelsByLevels);

    List<List<Integer>> zigzagResult = zigzagLevelOrder(root);
    System.out.println("锯齿遍历: " + zigzagResult);

    // 性能测试
    System.out.println("\n5. 性能测试:");
    TreeNode largeTree = createLargeTree(15); // 创建深度为15的完全二叉树

    System.out.println("大型树测试（深度15）:");
    performanceTest("前序遍历(递归)", largeTree, BinaryTreeTraversal::preorderRecursive);
    performanceTest("前序遍历(迭代)", largeTree, BinaryTreeTraversal::preorderIterative);
    performanceTest("中序遍历(递归)", largeTree, BinaryTreeTraversal::inorderRecursive);
    performanceTest("中序遍历(迭代)", largeTree, BinaryTreeTraversal::inorderIterative);
    performanceTest("后序遍历(递归)", largeTree, BinaryTreeTraversal::postorderRecursive);
    performanceTest("后序遍历(迭代)", largeTree, BinaryTreeTraversal::postorderIterative);
    performanceTest("层序遍历", largeTree, BinaryTreeTraversal::levelOrder);

    // 边界情况测试
    System.out.println("\n6. 边界情况测试:");

    // 空树
    System.out.println("空树测试:");
    System.out.println("前序: " + preorderRecursive(null));
    System.out.println("中序: " + inorderRecursive(null));
    System.out.println("后序: " + postorderRecursive(null));
    System.out.println("层序: " + levelOrder(null));

    // 单节点树
    TreeNode singleNode = new TreeNode(42);
    System.out.println("\n单节点树测试:");
    System.out.println("前序: " + preorderRecursive(singleNode));
    System.out.println("中序: " + inorderRecursive(singleNode));
    System.out.println("后序: " + postorderRecursive(singleNode));
    System.out.println("层序: " + levelOrder(singleNode));

    // 链式树（只有左子树）
    TreeNode chainTree = new TreeNode(1);
    chainTree.left = new TreeNode(2);
    chainTree.left.left = new TreeNode(3);
    chainTree.left.left.left = new TreeNode(4);

    System.out.println("\n链式树测试（只有左子树）:");
    System.out.println("前序: " + preorderRecursive(chainTree));
    System.out.println("中序: " + inorderRecursive(chainTree));
    System.out.println("后序: " + postorderRecursive(chainTree));
    System.out.println("层序: " + levelOrder(chainTree));
  }

  /**
   * 二叉树节点定义
   */
  public static class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
      this.val = val;
      this.left = null;
      this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}