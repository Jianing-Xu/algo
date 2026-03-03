package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：模拟消息队列
 *
 * 【题目描述】
 * 实现一个简单的消息队列。支持以下操作：
 * 1. push <msg>：将消息放入队列
 * 2. pull：从队列取出最早的消息
 * 如果队列为空则输出 "EMPTY"
 *
 * 【解题思路：直接使用 Queue（LinkedList）模拟】
 */
public class MessageQueue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            if (line.startsWith("push")) {
                String msg = line.substring(5);
                queue.offer(msg);
            } else if (line.equals("pull")) {
                if (queue.isEmpty()) {
                    System.out.println("EMPTY");
                } else {
                    System.out.println(queue.poll());
                }
            }
        }
    }
}
