package com.mythezone;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BankStatementAnalyzerGPT {
    public static final String RESOURCE = "src/main/resources";


    public static void main(String[] args) throws Exception {
        String filePath = "src/main/resources/bank-data-simple.csv"; // CSV文件路径
        analyzeBankStatements(filePath, 1); // 分析1月份的数据
    }

    public static void analyzeBankStatements(String filePath, int month) throws  Exception {
        double totalAmount = 0.0;
        double monthlyAmount = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;

            // 跳过标题行
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                LocalDate date = LocalDate.parse(nextLine[0], formatter);
                double amount = Double.parseDouble(nextLine[1]);

                // 累加总金额
                totalAmount += amount;

                // 如果是指定月份，则累加该月金额
                if (date.getMonthValue() == month) {
                    monthlyAmount += amount;
                }
            }

            System.out.printf("Total Amount: %.2f\n", totalAmount);
            System.out.printf("Amount for month %d: %.2f\n", month, monthlyAmount);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
