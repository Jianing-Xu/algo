package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：IP配置命令语法格式校验器
 *
 * 【题目描述】
 * 校验IP配置命令的格式是否合法。
 * 合法IP地址格式：A.B.C.D，其中A/B/C/D是0-255的整数，且没有前导零。
 * 合法子网掩码：连续的1后面跟连续的0（如255.255.255.0）。
 *
 * 【解题思路：字符串分割 + 规则校验】
 */
public class IpConfigValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();
        String[] parts = line.split(" ");

        if (parts.length < 2) {
            System.out.println("INVALID");
            return;
        }

        String ip = parts[0];
        String mask = parts.length > 1 ? parts[1] : "";

        boolean ipValid = isValidIp(ip);
        boolean maskValid = !mask.isEmpty() && isValidMask(mask);

        if (ipValid && maskValid) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
        }
    }

    private static boolean isValidIp(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;
        for (String part : parts) {
            if (part.isEmpty()) return false;
            if (part.length() > 1 && part.charAt(0) == '0') return false; // 前导零
            try {
                int val = Integer.parseInt(part);
                if (val < 0 || val > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidMask(String mask) {
        if (!isValidIp(mask)) return false;
        // 转成32位二进制，检查是否是连续的1后跟连续的0
        String[] parts = mask.split("\\.");
        long maskBits = 0;
        for (String part : parts) {
            maskBits = (maskBits << 8) | Integer.parseInt(part);
        }
        if (maskBits == 0) return false; // 全0不是合法掩码
        // 取反后+1应该是2的幂次
        long inverted = ~maskBits & 0xFFFFFFFFL;
        return (inverted & (inverted + 1)) == 0;
    }
}
