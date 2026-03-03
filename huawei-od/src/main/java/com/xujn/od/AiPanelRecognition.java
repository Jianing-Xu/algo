package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：AI面板识别
 *
 * 【题目描述】
 * 给定N个矩形面板的坐标(x1,y1,x2,y2)，判断哪些面板存在重叠。
 * 输出所有存在重叠的面板对。
 *
 * 【解题思路：两两判断矩形是否相交】
 * 两个矩形不相交的条件：一个在另一个的左/右/上/下方。
 * 取反即为相交条件。
 */
public class AiPanelRecognition {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] panels = new int[n][4]; // x1, y1, x2, y2
        for (int i = 0; i < n; i++) {
            panels[i][0] = sc.nextInt();
            panels[i][1] = sc.nextInt();
            panels[i][2] = sc.nextInt();
            panels[i][3] = sc.nextInt();
        }

        List<String> overlaps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isOverlap(panels[i], panels[j])) {
                    overlaps.add((i + 1) + " " + (j + 1));
                }
            }
        }

        if (overlaps.isEmpty()) {
            System.out.println("None");
        } else {
            for (String s : overlaps) System.out.println(s);
        }
    }

    private static boolean isOverlap(int[] a, int[] b) {
        // 不相交：a在b左边 || a在b右边 || a在b上面 || a在b下面
        return !(a[2] <= b[0] || a[0] >= b[2] || a[3] <= b[1] || a[1] >= b[3]);
    }
}
