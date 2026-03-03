package com.xujn.beauty.ch08_stack;

/**
 * 使用前后栈实现浏览器前进后退
 */
public class BrowserHistory {

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        browser.open("http://www.baidu.com");
        browser.open("http://www.news.baidu.com/");
        browser.open("http://www.news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }

    private String currentPage;
    private LinkedListStack backStack;
    private LinkedListStack forwardStack;

    public BrowserHistory() {
        this.backStack = new LinkedListStack();
        this.forwardStack = new LinkedListStack();
    }

    public void open(String url) {
        if (this.currentPage != null) {
            this.backStack.push(this.currentPage);
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack() {
        return this.backStack.size() > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.size() > 0;
    }

    public String goBack() {
        if (this.canGoBack()) {
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }
        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if (this.canGoForward()) {
            this.backStack.push(this.currentPage);
            String forwardUrl = this.forwardStack.pop();
            showUrl(forwardUrl, "Forward");
            return forwardUrl;
        }
        System.out.println("** Cannot go forward, no pages ahead.");
        return null;
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    /**
     * 为支持这道题的一个简单链表栈
     */
    public static class LinkedListStack {
        private int size;
        private Node top;

        static Node createNode(String data, Node next) {
            return new Node(data, next);
        }

        public void clear() {
            this.top = null;
            this.size = 0;
        }

        public void push(String data) {
            Node node = createNode(data, this.top);
            this.top = node;
            this.size++;
        }

        public String pop() {
            Node popNode = this.top;
            if (popNode == null) {
                System.out.println("Stack is empty.");
                return null;
            }
            this.top = popNode.next;
            if (this.size > 0) {
                this.size--;
            }
            return popNode.data;
        }

        public int size() {
            return this.size;
        }

        public void print() {
            Node currentNode = this.top;
            while (currentNode != null) {
                System.out.print(currentNode.data + "\t");
                currentNode = currentNode.next;
            }
            System.out.println();
        }

        public static class Node {
            private String data;
            private Node next;

            public Node(String data, Node next) {
                this.data = data;
                this.next = next;
            }

            public String getData() {
                return data;
            }
        }
    }
}
