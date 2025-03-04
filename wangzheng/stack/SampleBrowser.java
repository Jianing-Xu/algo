package stack;

import java.util.Stack;

// 编程模拟实现一个浏览器的前进、后退功能
public class SampleBrowser {

  String currentPage = "";

  Stack<String> forwardStack = new Stack<>();
  Stack<String> backStack = new Stack<>();

  public static void main(String[] args) {
    SampleBrowser browser = new SampleBrowser();
    browser.open("http://www.baidu.com");
    browser.open("http://news.baidu.com/");
    browser.open("http://news.baidu.com/ent");
    browser.back();
    browser.back();
    browser.forward();
    browser.open("http://www.qq.com");
    browser.forward();
    browser.back();
    browser.forward();
    browser.back();
    browser.back();
    browser.back();
    browser.back();
    browser.checkCurrentPage();
  }

  public void open(String url) {
    currentPage = url;
    backStack.push(url);
    showUrl(url, "open");
  }

  public void forward() {
    String url = forwardStack.pop();
    showUrl(url, "forward");
    backStack.push(currentPage);
  }

  public void back() {
    String url = backStack.pop();
    showUrl(url, "back");
    forwardStack.push(currentPage);
  }

  public void showUrl(String url, String prefix) {
    this.currentPage = url;
    System.out.println(prefix + " page == " + url);
  }

  public void checkCurrentPage() {
    System.out.println("Current page is: " + this.currentPage);
  }
}
