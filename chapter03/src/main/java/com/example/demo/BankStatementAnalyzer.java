package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

    //특정 파서와의 결합 제거..BankStatementParser 인터페이스 주입
    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + "a.txt");
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

        collectSummary(processor);

    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("the total for all transactions is "
                + bankStatementProcessor.calculateTotalAmount());

        System.out.println("the total for transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
    }



}
