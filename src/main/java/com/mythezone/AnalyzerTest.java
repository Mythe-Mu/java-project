package com.mythezone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnalyzerTest {
    private static final String BaseFolder = "src/main/resources";
    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get(BaseFolder, "bank_statement.csv");
        List<String> lines = Files.readAllLines(filePath);
        Double total = 0.0;


        for (String line : lines) {
            String[] columns = line.split(",");
            total += Double.parseDouble(columns[3]);

        }
        System.out.println(total);
        total = 0.0;
        for (String line : lines) {
            String[] columns = line.split(",");
            LocalDate date = LocalDate.parse(columns[0], DATE_FORMATER);
            if (date.getMonth() == Month.JANUARY){
                double amount = Double.parseDouble(columns[3]);
                total += amount;
            }
        }
//
        System.out.println(total);
    }
}
