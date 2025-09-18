package stack;

/**
 * 浏览器历史记录模拟器 使用两个栈实现浏览器的前进、后退功能
 */
public class BrowserHistory {

  private ArrayStack<String> backStack;       // 后退栈，存储历史页面
  private ArrayStack<String> forwardStack;    // 前进栈，存储前进页面
  private String currentPage;                 // 当前页面

  /**
   * 构造函数
   *
   * @param homepage 主页地址
   */
  public BrowserHistory(String homepage) {
    this.backStack = new ArrayStack<>();
    this.forwardStack = new ArrayStack<>();
    this.currentPage = homepage;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println("=== 浏览器历史记录模拟器测试 ===\n");

    // 创建浏览器历史记录，设置主页
    BrowserHistory browser = new BrowserHistory("www.google.com");
    browser.showStatus();

    // 访问一系列页面
    System.out.println("1. 访问页面序列:");
    browser.visit("www.github.com");
    browser.visit("www.stackoverflow.com");
    browser.visit("www.leetcode.com");
    browser.visit("www.baidu.com");
    browser.showStatus();

    // 测试后退功能
    System.out.println("2. 后退操作:");
    browser.back();     // 回到 leetcode
    browser.back();     // 回到 stackoverflow
    browser.showStatus();

    // 测试前进功能
    System.out.println("3. 前进操作:");
    browser.forward();  // 前进到 leetcode
    browser.showStatus();

    // 访问新页面（会清空前进历史）
    System.out.println("4. 访问新页面（清空前进历史）:");
    browser.visit("www.youtube.com");
    browser.showStatus();

    // 尝试前进（应该无法前进）
    System.out.println("5. 尝试前进:");
    browser.forward();
    browser.showStatus();

    // 连续后退到最开始
    System.out.println("6. 连续后退:");
    while (browser.canGoBack()) {
      browser.back();
    }
    browser.showStatus();

    // 尝试继续后退（应该无法后退）
    System.out.println("7. 尝试继续后退:");
    browser.back();
    browser.showStatus();

    // 清空历史记录
    System.out.println("8. 清空历史记录:");
    browser.clearHistory();
    browser.showStatus();
  }

  /**
   * 访问新页面 清空前进历史，将当前页面加入后退栈
   *
   * @param url 新页面地址
   */
  public void visit(String url) {
    if (currentPage != null) {
      backStack.push(currentPage);
    }
    currentPage = url;

    // 访问新页面时，清空前进栈
    forwardStack.clear();

    System.out.println("访问页面: " + url);
  }

  /**
   * 后退操作 将当前页面加入前进栈，从后退栈弹出页面作为当前页面
   *
   * @return 后退后的页面地址
   */
  public String back() {
    if (backStack.isEmpty()) {
      System.out.println("无法后退，已经是最早的页面");
      return currentPage;
    }

    // 将当前页面加入前进栈
    forwardStack.push(currentPage);

    // 从后退栈获取上一个页面
    currentPage = backStack.pop();

    System.out.println("后退到页面: " + currentPage);
    return currentPage;
  }

  /**
   * 前进操作 将当前页面加入后退栈，从前进栈弹出页面作为当前页面
   *
   * @return 前进后的页面地址
   */
  public String forward() {
    if (forwardStack.isEmpty()) {
      System.out.println("无法前进，已经是最新的页面");
      return currentPage;
    }

    // 将当前页面加入后退栈
    backStack.push(currentPage);

    // 从前进栈获取下一个页面
    currentPage = forwardStack.pop();

    System.out.println("前进到页面: " + currentPage);
    return currentPage;
  }

  /**
   * 获取当前页面
   *
   * @return 当前页面地址
   */
  public String getCurrentPage() {
    return currentPage;
  }

  /**
   * 判断是否可以后退
   *
   * @return true如果可以后退，否则false
   */
  public boolean canGoBack() {
    return !backStack.isEmpty();
  }

  /**
   * 判断是否可以前进
   *
   * @return true如果可以前进，否则false
   */
  public boolean canGoForward() {
    return !forwardStack.isEmpty();
  }

  /**
   * 获取后退历史记录数量
   *
   * @return 后退历史记录数量
   */
  public int getBackHistorySize() {
    return backStack.size();
  }

  /**
   * 获取前进历史记录数量
   *
   * @return 前进历史记录数量
   */
  public int getForwardHistorySize() {
    return forwardStack.size();
  }

  /**
   * 显示当前状态
   */
  public void showStatus() {
    System.out.println("=== 浏览器状态 ===");
    System.out.println("当前页面: " + currentPage);
    System.out.println("可后退: " + canGoBack() + " (历史记录: " + getBackHistorySize() + ")");
    System.out.println(
        "可前进: " + canGoForward() + " (历史记录: " + getForwardHistorySize() + ")");
    System.out.println();
  }

  /**
   * 清空所有历史记录
   */
  public void clearHistory() {
    backStack.clear();
    forwardStack.clear();
    System.out.println("已清空所有历史记录");
  }
}