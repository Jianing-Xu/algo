package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：整数编码
 *
 * 【题目描述】
 * 实现一种整数编码方法（类似 LEB128 / Varint 编码）：
 * 将整数转成二进制，每7位一组，从低位到高位分组。
 * 每组的最高位如果后面还有更多分组则设为1，否则设为0。
 * 输出每组对应的十六进制表示。
 *
 * 【解题思路：位运算模拟】
 * 1. 取整数的低7位作为一组
 * 2. 右移7位
 * 3. 如果移位后还有剩余，则当前组最高位(第8位)设为1
 * 4. 重复直到整数为0
 * 5. 将每组转成2位十六进制输出
 */
public class IntegerEncoding {

    public static String encode(int num) {
        if (num == 0) return "00";

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int low7 = num & 0x7F; // 取低7位
            num >>= 7;             // 右移7位

            if (num > 0) {
                low7 |= 0x80; // 还有后续，最高位设为1
            }

            sb.append(String.format("%02X", low7));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            System.out.println(encode(num));
        }
    }
}
