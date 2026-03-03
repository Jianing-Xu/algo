package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：构造数列
 *
 * 【题目描述】
 * 按照以下规则构建数列：
 * - 数列第一个数是 n
 * - 如果当前数是偶数，下一个数是当前数 / 2
 * - 如果当前数是奇数，下一个数是当前数 * 3 + 1
 * - 数列直到出现 1 为止
 * 输出整个数列。
 *
 * 【解题思路：Collatz 猜想模拟】
 */
public class ConstructSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        List<Long> sequence = new ArrayList<>();
        sequence.add(n);

        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = n * 3 + 1;
            }
            sequence.add(n);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sequence.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(sequence.get(i));
        }
        System.out.println(sb.toString());
    }
}
