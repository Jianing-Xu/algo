package linkedlist;

/**
 * 链表工具类 实现链表相关的算法操作
 */
public class LinkedListUtils {

  /**
   * 反转单链表 使用迭代方法，时间复杂度O(n)，空间复杂度O(1)
   *
   * @param head 链表头节点
   * @return 反转后的头节点
   */
  public static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode current = head;

    while (current != null) {
      ListNode next = current.next;   // 保存下一个节点
      current.next = prev;            // 反转当前节点的指针
      prev = current;                 // 移动prev指针
      current = next;                 // 移动current指针
    }

    return prev; // prev成为新的头节点
  }

  /**
   * 递归方式反转单链表 时间复杂度O(n)，空间复杂度O(n)
   *
   * @param head 链表头节点
   * @return 反转后的头节点
   */
  public static ListNode reverseListRecursive(ListNode head) {
    // 基础情况：空链表或只有一个节点
    if (head == null || head.next == null) {
      return head;
    }

    // 递归反转后面的链表
    ListNode newHead = reverseListRecursive(head.next);

    // 反转当前节点
    head.next.next = head;
    head.next = null;

    return newHead;
  }

  /**
   * 合并两个有序链表 时间复杂度O(m+n)，空间复杂度O(1)
   *
   * @param list1 第一个有序链表
   * @param list2 第二个有序链表
   * @return 合并后的有序链表头节点
   */
  public static ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
    // 创建哨兵节点，简化边界处理
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    // 比较两个链表的节点值，选择较小的加入结果链表
    while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
        current.next = list1;
        list1 = list1.next;
      } else {
        current.next = list2;
        list2 = list2.next;
      }
      current = current.next;
    }

    // 连接剩余的节点
    current.next = (list1 != null) ? list1 : list2;

    return dummy.next;
  }

  /**
   * 递归方式合并两个有序链表 时间复杂度O(m+n)，空间复杂度O(m+n)
   *
   * @param list1 第一个有序链表
   * @param list2 第二个有序链表
   * @return 合并后的有序链表头节点
   */
  public static ListNode mergeTwoSortedListsRecursive(ListNode list1, ListNode list2) {
    // 基础情况
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }

    // 递归合并
    if (list1.val <= list2.val) {
      list1.next = mergeTwoSortedListsRecursive(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeTwoSortedListsRecursive(list1, list2.next);
      return list2;
    }
  }

  /**
   * 寻找链表的中间节点 使用快慢指针技术，时间复杂度O(n)，空间复杂度O(1)
   *
   * @param head 链表头节点
   * @return 中间节点（如果有两个中间节点，返回第二个）
   */
  public static ListNode findMiddleNode(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;   // 慢指针，每次移动一步
    ListNode fast = head;   // 快指针，每次移动两步

    // 当快指针到达末尾时，慢指针正好在中间
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  /**
   * 寻找链表的中间节点（返回第一个中间节点）
   *
   * @param head 链表头节点
   * @return 中间节点（如果有两个中间节点，返回第一个）
   */
  public static ListNode findMiddleNodeFirst(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head.next; // 快指针从第二个节点开始

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  /**
   * 检测链表是否有环 使用快慢指针技术（Floyd判圈算法）
   *
   * @param head 链表头节点
   * @return true如果有环，否则false
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head.next;

    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }

    return true;
  }

  /**
   * 删除链表的倒数第n个节点 使用双指针技术，一次遍历完成
   *
   * @param head 链表头节点
   * @param n    倒数第n个
   * @return 删除节点后的头节点
   */
  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode first = dummy;
    ListNode second = dummy;

    // 让first指针先走n+1步
    for (int i = 0; i <= n; i++) {
      first = first.next;
    }

    // 两个指针同时移动，直到first到达末尾
    while (first != null) {
      first = first.next;
      second = second.next;
    }

    // 删除倒数第n个节点
    second.next = second.next.next;

    return dummy.next;
  }

  /**
   * 创建链表（用于测试）
   *
   * @param values 节点值数组
   * @return 链表头节点
   */
  public static ListNode createList(int[] values) {
    if (values == null || values.length == 0) {
      return null;
    }

    ListNode head = new ListNode(values[0]);
    ListNode current = head;

    for (int i = 1; i < values.length; i++) {
      current.next = new ListNode(values[i]);
      current = current.next;
    }

    return head;
  }

  /**
   * 打印链表
   *
   * @param head 链表头节点
   * @param name 链表名称
   */
  public static void printList(ListNode head, String name) {
    System.out.print(name + ": ");
    ListNode current = head;
    while (current != null) {
      System.out.print(current.val);
      if (current.next != null) {
        System.out.print(" -> ");
      }
      current = current.next;
    }
    System.out.println();
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 链表算法测试 ===");

    // 测试反转链表
    System.out.println("\n1. 反转链表测试:");
    ListNode list1 = createList(new int[]{1, 2, 3, 4, 5});
    printList(list1, "原链表");
    ListNode reversed = reverseList(list1);
    printList(reversed, "反转后");

    // 测试合并有序链表
    System.out.println("\n2. 合并有序链表测试:");
    ListNode list2 = createList(new int[]{1, 3, 5});
    ListNode list3 = createList(new int[]{2, 4, 6});
    printList(list2, "链表1");
    printList(list3, "链表2");
    ListNode merged = mergeTwoSortedLists(list2, list3);
    printList(merged, "合并后");

    // 测试寻找中间节点
    System.out.println("\n3. 寻找中间节点测试:");
    ListNode list4 = createList(new int[]{1, 2, 3, 4, 5});
    ListNode list5 = createList(new int[]{1, 2, 3, 4, 5, 6});
    printList(list4, "奇数长度链表");
    ListNode middle1 = findMiddleNode(list4);
    System.out.println("中间节点值: " + middle1.val);

    printList(list5, "偶数长度链表");
    ListNode middle2 = findMiddleNode(list5);
    System.out.println("中间节点值: " + middle2.val);
  }

  /**
   * 简单链表节点定义
   */
  public static class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
      this.val = val;
      this.next = null;
    }

    public ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}