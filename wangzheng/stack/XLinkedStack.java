package stack;

import utils.ListNode;
import utils.PrintUtil;

// 用链表实现一个链式栈
public class XLinkedStack {

  ListNode top;
  int size;

  public static void main(String[] args) {
    XLinkedStack stack = new XLinkedStack();
    stack.push(1);
    stack.push(3);
    stack.printStack();
    int pop = stack.pop();
    System.out.println(pop);
    stack.push(5);
    stack.printStack();
  }

  public boolean push(int value) {
    if (top == null) {
      top = new ListNode(value);
    } else {
      ListNode node = new ListNode(value);
      node.next = top;
      top = node;
    }
    size++;
    return true;
  }

  public int pop() {
    if (top != null) {
      int value = top.val;
      top = top.next;
      size--;
      return value;
    } else {
      return -1;
    }
  }

  public void printStack() {
    PrintUtil.printLinkedList(top);
  }
}
