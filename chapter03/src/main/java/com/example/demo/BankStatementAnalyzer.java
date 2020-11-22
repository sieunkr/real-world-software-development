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


        //TODO: 새로운 요구사항이 있을때마다 별도의 클래스를 만들어야 한다.. 람다 표현식으로 변경 가능.
        final List<BankTransaction> transactions = processor.findTransactions(new BankTransactionIsInFebruaryAndExpensive());


        //3.4.2 람다 표현식
        final List<BankTransaction> transactions1 = processor.findTransactions(bankTransaction ->
                bankTransaction.getDate().getMonth() == Month.JANUARY);
        /*

        //Predicate 를 사용할 수도 있음. 참고만..

        기존 메서드를 바꿀필요 없이 새로운 구현을 인수로 전달하게 된다. 변경 없이도(closed), 확장성은 개방(open) 된다..

        기존 코드를 바꾸지 않으므로 기존 코드가 잘못될 가능성이 줄어든다.
        코드가 중복되지 않으므로 기존 코드의 재사용성이 높다.
        결합도가 낮아지므로 코드 유지보수성이 좋다.
         */

    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("the total for all transactions is "
                + bankStatementProcessor.calculateTotalAmount());

        System.out.println("the total for transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
    }



}
