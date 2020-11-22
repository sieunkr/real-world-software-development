package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        //예제 2-1
        //final Path path = Paths.get(RESOURCES + args[0]);
        final Path path = Paths.get(RESOURCES + "a.txt");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println("The total for all transactions is " + total);
         */


        /*
        //예제 2-2
        //final Path path = Paths.get(RESOURCES = args[0]);
        final Path path = Paths.get(RESOURCES + "a.txt");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (final String line : lines) {

            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);

            if(date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }

        System.out.println("The total for all transactions is " + total);
        */



        //예제 2.3, 2.4 BankStatementCSVParser, BankTransaction 클래스 생성

        /*
        현재 메인 클래스는 여러 책임을 모두 포함하므로 이를 개별로 분리해야 한다.
        1.입력 읽기
        2.주어진 형식의 입력 파싱
        3.결과 처리
        4.결과 요약 리포트
         */

        // 2.5, 2.6 
        final Path path = Paths.get(RESOURCES + "a.txt");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        BankStatementCSVParser parser = new BankStatementCSVParser();
        List<BankTransaction> list = parser.parseLinesFromCSV(lines);

        /*
        total = list.stream()
                .filter(b -> b.getDate().getMonth().getValue() == 1)
                .mapToDouble(BankTransaction::getAmount)
                .sum();

         */


        total = calculateTotalAmount(list);

        System.out.println("The total for all transactions is " + total);
    }

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;
        for(final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }


}