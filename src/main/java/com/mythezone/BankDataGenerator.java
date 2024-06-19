package com.mythezone;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BankDataGenerator {

    private static final String[] CATEGORIES = {
            "Groceries", "Dining", "Entertainment", "Transportation",
            "Utilities", "Health", "Fitness", "Pets", "Insurance", "Shopping"
    };

    public static void main(String[] args) {
        String filePath = "src/main/resources/bank_statement.csv";
        generateBankStatementData(filePath, 500); // 生成500条记录
    }

    public static void generateBankStatementData(String filePath, int entries) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Date,Description,Category,Amount\n");

            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (int i = 0; i < entries; i++) {
                LocalDate date = LocalDate.ofYearDay(2023, random.nextInt(365) + 1);
                String description = "Transaction " + (i + 1);
                String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
                double amount = (random.nextDouble() * 600) - 300; // 随机金额范围[-300, 300]
                writer.append(String.format("%s,%s,%s,%.2f\n", date.format(formatter), description, category, amount));
            }

            System.out.println("Data generation completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
