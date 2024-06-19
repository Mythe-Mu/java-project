package com.mythezone;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BankStatementSort {

    public static void main(String[] args) {
        String filePath = "src/main/resources/bank_statement.csv";
        sortBankStatementByDate(filePath);
    }

    public static void sortBankStatementByDate(String filePath) {
        Path path = Paths.get(filePath);

        try {
            // 读取所有行
            List<String> lines = Files.readAllLines(path);
            if (lines.size() <= 1) return; // 如果只有标题或文件为空，则直接返回

            // 移除标题行，稍后会再加回去
            String header = lines.remove(0);

            // 按日期排序
            lines.sort(Comparator.comparing(line -> line.split(",")[0]));

            // 将标题行加回
            lines.add(0, header);

            // 写回文件
            Files.write(path, lines);
            System.out.println("Bank statement sorted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

