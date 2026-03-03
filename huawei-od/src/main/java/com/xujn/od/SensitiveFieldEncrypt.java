package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：敏感字段加密
 *
 * 【题目描述】
 * 给定一个由多个命令字拼接的字符串（用下划线分隔），
 * 以及需要加密的命令字的位置K。
 * 对第K个命令字进行SHA256加密后输出整个字符串。
 * 如果K超出范围，输出原字符串。
 *
 * 【解题思路：字符串分割 + 模拟】
 * （简化版本，用***代替加密操作来演示思路）
 */
public class SensitiveFieldEncrypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine().trim());
        String str = sc.nextLine().trim();

        String[] parts = str.split("_");

        if (k < 1 || k > parts.length) {
            System.out.println(str);
            return;
        }

        // 对第K个字段（1-indexed）加密
        parts[k - 1] = encrypt(parts[k - 1]);
        System.out.println(String.join("_", parts));
    }

    private static String encrypt(String s) {
        // 简化版加密：这里演示用***替代，实际场景中可以使用SHA256
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "******";
        }
    }
}
