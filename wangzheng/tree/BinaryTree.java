package tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import utils.PrintUtil;
import utils.TreeNode;

/**
 * @author xujn
 * @date 2025-03-06
 */
public class BinaryTree {

  public static void main(String[] args) {
    TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    // preOrder(root);
    // inOrder(root);
    // postOrder(root);
    // levelOrder(root);
    PrintUtil.printTree(root);
  }

  public static void preOrder(TreeNode root) {
    if (root == null) {
      return;
    }
    System.out.println(root.val);
    preOrder(root.left);
    preOrder(root.right);
  }

  public static void inOrder(TreeNode root) {
    if (root == null) {
      return;
    }
    inOrder(root.left);
    System.out.println(root.val);
    inOrder(root.right);
  }

  public static void postOrder(TreeNode root) {
    if (root == null) {
      return;
    }
    postOrder(root.left);
    postOrder(root.right);
    System.out.println(root.val);
  }

  public static void levelOrder(TreeNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      System.out.println(node.val);
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
  }
}
