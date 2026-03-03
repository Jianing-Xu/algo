package com.xujn.od;

import java.util.*;

/**
 * 华为OD机考题：Excel单元格数值统计
 *
 * 【题目描述】
 * 给定一个Excel表格区域（如 "A1:C3"），和其中部分单元格的值，
 * 求指定区域内所有单元格数值的总和/平均值/最大值等统计信息。
 *
 * 【解题思路：模拟 + 字符串解析】
 * 解析Excel列号（A=1,B=2...Z=26,AA=27...）和行号，
 * 将单元格映射到二维数组，然后做统计。
 */
public class ExcelCellStatistic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 数据条数
        sc.nextLine();

        Map<String, Integer> cellValues = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("=");
            String cell = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());
            cellValues.put(cell, value);
        }

        String range = sc.nextLine().trim(); // 如 "A1:C3"
        String[] rangeParts = range.split(":");
        String topLeft = rangeParts[0];
        String bottomRight = rangeParts[1];

        int startCol = parseCol(topLeft);
        int startRow = parseRow(topLeft);
        int endCol = parseCol(bottomRight);
        int endRow = parseRow(bottomRight);

        long sum = 0;
        int count = 0;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                String cellName = colToStr(c) + r;
                if (cellValues.containsKey(cellName)) {
                    sum += cellValues.get(cellName);
                }
                count++;
            }
        }

        System.out.println(sum);
    }

    static int parseCol(String cell) {
        int col = 0;
        for (int i = 0; i < cell.length(); i++) {
            char c = cell.charAt(i);
            if (Character.isLetter(c)) {
                col = col * 26 + (c - 'A' + 1);
            } else break;
        }
        return col;
    }

    static int parseRow(String cell) {
        StringBuilder sb = new StringBuilder();
        for (char c : cell.toCharArray()) {
            if (Character.isDigit(c)) sb.append(c);
        }
        return Integer.parseInt(sb.toString());
    }

    static String colToStr(int col) {
        StringBuilder sb = new StringBuilder();
        while (col > 0) {
            col--;
            sb.insert(0, (char) ('A' + col % 26));
            col /= 26;
        }
        return sb.toString();
    }
}
