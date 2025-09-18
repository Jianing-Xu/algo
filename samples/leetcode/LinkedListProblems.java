package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * LeetCode链表相关问题解答 包含环形链表检测、合并K个排序链表等经典题目
 */
public class LinkedListProblems {

  /**
   * LeetCode 141. 环形链表 给定一个链表，判断链表中是否有环
   * <p>
   * 解题思路：Floyd判圈算法（快慢指针） 1. 使用两个指针，快指针每次走两步，慢指针每次走一步 2. 如果有环，快慢指针最终会相遇 3. 如果无环，快指针会先到达链表末尾
   * <p>
   * 时间复杂度：O(n) 空间复杂度：O(1)
   *
   * @param head 链表头节点
   * @return 是否有环
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head.next;

    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false; // 快指针到达末尾，无环
      }
      slow = slow.next;        // 慢指针走一步
      fast = fast.next.next;   // 快指针走两步
    }

    return true; // 快慢指针相遇，有环
  }

  // ==================== Linked List Cycle I（环形链表）====================

  /**
   * LeetCode 142. 环形链表 II 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回 null
   * <p>
   * 解题思路： 1. 先用快慢指针判断是否有环 2. 如果有环，将一个指针重置到头节点 3. 两个指针同时每次走一步，相遇点就是环的入口
   * <p>
   * 数学原理： 设链表头到环入口距离为a，环入口到相遇点距离为b，相遇点到环入口距离为c 慢指针走过距离：a + b 快指针走过距离：a + b + c + b = a + 2b + c
   * 由于快指针速度是慢指针的2倍：a + 2b + c = 2(a + b) 化简得：c = a
   *
   * @param head 链表头节点
   * @return 环的入口节点，无环返回null
   */
  public static ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }

    // 第一步：判断是否有环
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        // 第二步：找到环的入口
        ListNode ptr1 = head;
        ListNode ptr2 = slow;

        while (ptr1 != ptr2) {
          ptr1 = ptr1.next;
          ptr2 = ptr2.next;
        }

        return ptr1; // 环的入口
      }
    }

    return null; // 无环
  }

  /**
   * 使用哈希表检测环（空间复杂度O(n)的解法）
   *
   * @param head 链表头节点
   * @return 是否有环
   */
  public static boolean hasCycleHashSet(ListNode head) {
    Set<ListNode> visited = new HashSet<>();

    while (head != null) {
      if (visited.contains(head)) {
        return true;
      }
      visited.add(head);
      head = head.next;
    }

    return false;
  }

  /**
   * LeetCode 23. 合并K个升序链表 给你一个链表数组，每个链表都已经按升序排列 请你将所有链表合并到一个升序链表中，返回合并后的链表
   * <p>
   * 解题思路：分治法 1. 将K个链表两两配对进行合并 2. 递归地合并，直到只剩一个链表
   * <p>
   * 时间复杂度：O(N log k)，其中N是所有链表节点总数，k是链表个数 空间复杂度：O(log k)，递归栈的深度
   *
   * @param lists 链表数组
   * @return 合并后的链表
   */
  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    return mergeKListsHelper(lists, 0, lists.length - 1);
  }

  // ==================== Merge k Sorted Lists（合并K个排序链表）====================

  /**
   * 分治合并的辅助方法
   *
   * @param lists 链表数组
   * @param left  左边界
   * @param right 右边界
   * @return 合并后的链表
   */
  private static ListNode mergeKListsHelper(ListNode[] lists, int left, int right) {
    if (left == right) {
      return lists[left];
    }

    if (left > right) {
      return null;
    }

    int mid = left + (right - left) / 2;
    ListNode leftList = mergeKListsHelper(lists, left, mid);
    ListNode rightList = mergeKListsHelper(lists, mid + 1, right);

    return mergeTwoLists(leftList, rightList);
  }

  /**
   * 合并两个有序链表 LeetCode 21. 合并两个有序链表
   *
   * @param l1 第一个链表
   * @param l2 第二个链表
   * @return 合并后的链表
   */
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        current.next = l1;
        l1 = l1.next;
      } else {
        current.next = l2;
        l2 = l2.next;
      }
      current = current.next;
    }

    // 连接剩余节点
    current.next = (l1 != null) ? l1 : l2;

    return dummy.next;
  }

  /**
   * 使用优先队列合并K个链表 时间复杂度：O(N log k) 空间复杂度：O(k)
   *
   * @param lists 链表数组
   * @return 合并后的链表
   */
  public static ListNode mergeKListsPriorityQueue(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    // 使用优先队列（最小堆）
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

    // 将每个链表的头节点加入优先队列
    for (ListNode list : lists) {
      if (list != null) {
        pq.offer(list);
      }
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (!pq.isEmpty()) {
      ListNode node = pq.poll();
      current.next = node;
      current = current.next;

      if (node.next != null) {
        pq.offer(node.next);
      }
    }

    return dummy.next;
  }

  /**
   * 逐一合并的方法（效率较低） 时间复杂度：O(N * k) 空间复杂度：O(1)
   *
   * @param lists 链表数组
   * @return 合并后的链表
   */
  public static ListNode mergeKListsSequential(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    ListNode result = lists[0];
    for (int i = 1; i < lists.length; i++) {
      result = mergeTwoLists(result, lists[i]);
    }

    return result;
  }

  /**
   * 创建链表
   *
   * @param values 节点值数组
   * @return 链表头节点
   */
  public static ListNode createList(int[] values) {
    if (values == null || values.length == 0) {
      return null;
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    for (int val : values) {
      current.next = new ListNode(val);
      current = current.next;
    }

    return dummy.next;
  }

  // ==================== 辅助方法 ====================

  /**
   * 创建有环链表
   *
   * @param values     节点值数组
   * @param cycleIndex 环开始的索引，-1表示无环
   * @return 链表头节点
   */
  public static ListNode createCyclicList(int[] values, int cycleIndex) {
    if (values == null || values.length == 0) {
      return null;
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    ListNode cycleStart = null;

    for (int i = 0; i < values.length; i++) {
      current.next = new ListNode(values[i]);
      current = current.next;

      if (i == cycleIndex) {
        cycleStart = current;
      }
    }

    // 创建环
    if (cycleIndex >= 0 && cycleStart != null) {
      current.next = cycleStart;
    }

    return dummy.next;
  }

  /**
   * 打印链表（处理环形链表）
   *
   * @param head     链表头节点
   * @param maxNodes 最大打印节点数
   */
  public static void printList(ListNode head, int maxNodes) {
    Set<ListNode> visited = new HashSet<>();
    int count = 0;

    while (head != null && count < maxNodes) {
      if (visited.contains(head)) {
        System.out.print(" -> [环检测到]");
        break;
      }

      visited.add(head);
      System.out.print(head.val);

      if (head.next != null && count < maxNodes - 1) {
        System.out.print(" -> ");
      }

      head = head.next;
      count++;
    }

    if (count == maxNodes && head != null) {
      System.out.print(" -> ...");
    }

    System.out.println();
  }

  /**
   * 计算链表长度（处理环形链表）
   *
   * @param head 链表头节点
   * @return 链表长度，如果有环返回-1
   */
  public static int getListLength(ListNode head) {
    Set<ListNode> visited = new HashSet<>();
    int length = 0;

    while (head != null) {
      if (visited.contains(head)) {
        return -1; // 有环
      }
      visited.add(head);
      length++;
      head = head.next;
    }

    return length;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== LeetCode 链表问题测试 ===\n");

    // 测试1：环形链表检测
    System.out.println("1. 环形链表检测测试:");

    // 创建无环链表：1 -> 2 -> 3 -> 4 -> null
    ListNode list1 = createList(new int[]{1, 2, 3, 4});
    System.out.print("无环链表: ");
    printList(list1, 10);

    long startTime = System.nanoTime();
    boolean hasCycle1 = hasCycle(list1);
    long cycleTime1 = System.nanoTime() - startTime;

    startTime = System.nanoTime();
    boolean hasCycleHash1 = hasCycleHashSet(list1);
    long cycleHashTime1 = System.nanoTime() - startTime;

    System.out.println("快慢指针检测: " + hasCycle1 + ", 耗时: " + (cycleTime1 / 1000.0) + "μs");
    System.out.println(
        "哈希表检测: " + hasCycleHash1 + ", 耗时: " + (cycleHashTime1 / 1000.0) + "μs");
    System.out.println("结果一致性: " + (hasCycle1 == hasCycleHash1));

    // 创建有环链表：1 -> 2 -> 3 -> 4 -> 2 (环)
    ListNode list2 = createCyclicList(new int[]{1, 2, 3, 4}, 1);
    System.out.print("\n有环链表: ");
    printList(list2, 10);

    startTime = System.nanoTime();
    boolean hasCycle2 = hasCycle(list2);
    long cycleTime2 = System.nanoTime() - startTime;

    startTime = System.nanoTime();
    ListNode cycleStart = detectCycle(list2);
    long detectTime = System.nanoTime() - startTime;

    System.out.println("快慢指针检测: " + hasCycle2 + ", 耗时: " + (cycleTime2 / 1000.0) + "μs");
    System.out.println("环入口检测: " + (cycleStart != null ? cycleStart.val : "null") +
        ", 耗时: " + (detectTime / 1000.0) + "μs");

    // 测试2：合并K个排序链表
    System.out.println("\n2. 合并K个排序链表测试:");

    ListNode[] lists = {
        createList(new int[]{1, 4, 5}),
        createList(new int[]{1, 3, 4}),
        createList(new int[]{2, 6})
    };

    System.out.println("输入链表:");
    for (int i = 0; i < lists.length; i++) {
      System.out.print("链表" + (i + 1) + ": ");
      printList(lists[i], 10);
    }

    // 测试分治法
    startTime = System.nanoTime();
    ListNode merged1 = mergeKLists(lists.clone());
    long mergeTime1 = System.nanoTime() - startTime;

    // 测试优先队列法
    startTime = System.nanoTime();
    ListNode merged2 = mergeKListsPriorityQueue(lists.clone());
    long mergeTime2 = System.nanoTime() - startTime;

    // 测试逐一合并法
    startTime = System.nanoTime();
    ListNode merged3 = mergeKListsSequential(lists.clone());
    long mergeTime3 = System.nanoTime() - startTime;

    System.out.println("\n合并结果:");
    System.out.print("分治法: ");
    printList(merged1, 20);
    System.out.println("耗时: " + (mergeTime1 / 1000.0) + "μs");

    System.out.print("优先队列: ");
    printList(merged2, 20);
    System.out.println("耗时: " + (mergeTime2 / 1000.0) + "μs");

    System.out.print("逐一合并: ");
    printList(merged3, 20);
    System.out.println("耗时: " + (mergeTime3 / 1000.0) + "μs");

    // 测试3：性能测试
    System.out.println("\n3. 性能测试:");

    // 环检测性能测试
    System.out.println("环检测性能测试:");
    for (int size : new int[]{1000, 5000, 10000}) {
      // 创建长链表
      int[] values = new int[size];
      for (int i = 0; i < size; i++) {
        values[i] = i;
      }

      ListNode longList = createList(values);

      startTime = System.currentTimeMillis();
      boolean result1 = hasCycle(longList);
      long time1 = System.currentTimeMillis() - startTime;

      startTime = System.currentTimeMillis();
      boolean result2 = hasCycleHashSet(longList);
      long time2 = System.currentTimeMillis() - startTime;

      System.out.printf("链表长度=%d: 快慢指针=%dms, 哈希表=%dms%n",
          size, time1, time2);
    }

    // 合并K个链表性能测试
    System.out.println("\n合并K个链表性能测试:");
    Random random = new Random(42);

    for (int k : new int[]{10, 50, 100}) {
      ListNode[] perfLists = new ListNode[k];

      for (int i = 0; i < k; i++) {
        int listSize = 10 + random.nextInt(20);
        int[] listValues = new int[listSize];

        for (int j = 0; j < listSize; j++) {
          listValues[j] = random.nextInt(1000);
        }
        Arrays.sort(listValues); // 确保有序

        perfLists[i] = createList(listValues);
      }

      startTime = System.currentTimeMillis();
      ListNode result1 = mergeKLists(perfLists.clone());
      long time1 = System.currentTimeMillis() - startTime;

      startTime = System.currentTimeMillis();
      ListNode result2 = mergeKListsPriorityQueue(perfLists.clone());
      long time2 = System.currentTimeMillis() - startTime;

      startTime = System.currentTimeMillis();
      ListNode result3 = mergeKListsSequential(perfLists.clone());
      long time3 = System.currentTimeMillis() - startTime;

      System.out.printf("K=%d: 分治=%dms, 优先队列=%dms, 逐一合并=%dms%n",
          k, time1, time2, time3);
    }

    // 测试4：边界情况
    System.out.println("\n4. 边界情况测试:");

    // 环检测边界情况
    System.out.println("环检测边界情况:");
    System.out.println("空链表: " + hasCycle(null));
    System.out.println("单节点无环: " + hasCycle(new ListNode(1)));

    // 单节点有环
    ListNode singleCycle = new ListNode(1);
    singleCycle.next = singleCycle;
    System.out.println("单节点有环: " + hasCycle(singleCycle));

    // 合并链表边界情况
    System.out.println("\n合并链表边界情况:");
    System.out.print("空数组: ");
    printList(mergeKLists(new ListNode[]{}), 10);

    System.out.print("包含null: ");
    printList(mergeKLists(new ListNode[]{null, createList(new int[]{1, 2}), null}), 10);

    System.out.print("单个链表: ");
    printList(mergeKLists(new ListNode[]{createList(new int[]{1, 2, 3})}), 10);

    // 测试5：复杂环形链表
    System.out.println("\n5. 复杂环形链表测试:");

    // 创建复杂环形链表：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 3 (环从3开始)
    ListNode complexCycle = createCyclicList(new int[]{1, 2, 3, 4, 5, 6}, 2);

    System.out.print("复杂环形链表: ");
    printList(complexCycle, 15);

    boolean hasComplexCycle = hasCycle(complexCycle);
    ListNode complexCycleStart = detectCycle(complexCycle);

    System.out.println("是否有环: " + hasComplexCycle);
    System.out.println("环入口值: " + (complexCycleStart != null ? complexCycleStart.val : "null"));
  }

  /**
   * 链表节点定义
   */
  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      ListNode current = this;
      Set<ListNode> visited = new HashSet<>();

      while (current != null && !visited.contains(current)) {
        visited.add(current);
        sb.append(current.val);
        if (current.next != null && !visited.contains(current.next)) {
          sb.append(" -> ");
        }
        current = current.next;
      }

      if (current != null) {
        sb.append(" -> [环]");
      }

      return sb.toString();
    }
  }
}