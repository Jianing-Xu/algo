package com.xujn.interviewguide.string;

/**
 * 判断两个字符串是否互为旋转词。
 */
public class IsRotation {

    public boolean isRotation(String first, String second) {
        if (first == null || second == null || first.length() != second.length()) {
            return false;
        }
        return (first + first).contains(second);
    }
}
