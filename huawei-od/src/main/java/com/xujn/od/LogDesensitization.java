package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：日志脱敏
 *
 * 【题目描述】
 * 给定一条日志字符串，对其中的敏感信息进行脱敏处理。
 * 规则：用 "***" 替换日志中所有连续的数字串（如手机号、身份证号等）。
 *
 * 【解题思路：正则表达式替换】
 */
public class LogDesensitization {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String log = sc.nextLine();
        // 将所有连续数字替换成 "***"
        String result = log.replaceAll("\\d+", "***");
        System.out.println(result);
    }
}
