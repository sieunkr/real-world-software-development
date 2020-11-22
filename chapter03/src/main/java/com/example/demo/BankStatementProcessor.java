package com.example.demo;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

//예제 2.7
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactionList;

    public BankStatementProcessor(final List<BankTransaction> bankTransactionList) {
        this.bankTransactionList = bankTransactionList;
    }

    public double calculateTotalAmount() {
        double total = 0d;
        for(final BankTransaction bankTransaction : bankTransactionList) {
            total += bankTransaction.getAmount();
        }
        /*
        double total = 0d;
        for(final BankTransaction bankTransaction : bankTransactionList) {
            total += bankTransaction.getAmount();
        }
        /*
        total = list.stream()
                .filter(b -> b.getDate().getMonth().getValue() == 1)
                .mapToDouble(BankTransaction::getAmount)
                .sum();

         */
        return total;

    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        total = bankTransactionList.stream()
                .filter(b -> b.getDate().getMonth() == month)
                .mapToDouble(BankTransaction::getAmount)
                .sum();

        /*
        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }

         */
        return total;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {

        final List<BankTransaction> result = new ArrayList<>();

        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsInMonth(final Month month) {

        final List<BankTransaction> result = new ArrayList<>();

        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getDate().getMonth() == month) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    //예제 3.3 개방/폐쇄 원칙 적용 전..
    //거래 내역의 여러 속성을 조합할수록 코드가 점점 복잡해진다.
    public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {

        final List<BankTransaction> result = new ArrayList<>();

        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    //개방/폐쇄 원칙을 적용한 후 유연해진..
    public List<BankTransaction> findTransactions(final BankTransactionFilter filter) {
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction : bankTransactionList) {
            if(filter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }
}