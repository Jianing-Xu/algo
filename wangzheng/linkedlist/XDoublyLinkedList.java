package linkedlist;

// 双向链表
public class XDoublyLinkedList {

  Node head;
  Node tail;

  public XDoublyLinkedList() {
    Node node = new Node(0);
    head = node;
    tail = node;
  }

  public static void main(String[] args) {
    XDoublyLinkedList list = new XDoublyLinkedList();
    list.insertTail(1);
    list.insertTail(2);
    list.insertTail(3);

    list.delete(2);

    list.printList();
  }

  public void insertToHead(int value) {
    Node node = new Node(value);
    node.next = head.next;
    node.prev = head;
    head.next = node;
  }

  public void insertTail(int value) {
    Node node = new Node(value);
    tail.next = node;
    node.prev = tail;
    tail = node;
  }

  public void delete(int value) {
    Node current = head;
    while (current != null) {
      if (current.val == value) {
        if (current.prev != null) {
          current.prev.next = current.next;
        } else {
          head = current.next;
        }
        if (current.next != null) {
          current.next.prev = current.prev;
        } else {
          tail = current.prev;
        }
        return;
      }
      current = current.next;
    }
  }

  public void printList() {
    Node current = head;
    while (current != null) {
      System.out.print(current.val + " ");
      current = current.next;
    }
    System.out.println();
  }

  class Node {

    Node prev;
    int val;
    Node next;

    public Node(int val) {
      this.val = val;
    }

    public Node(Node prev, int val, Node next) {
      this.prev = prev;
      this.val = val;
      this.next = next;
    }
  }
}
