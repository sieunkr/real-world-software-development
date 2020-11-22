package com.example.demo;

import java.time.Month;
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

    public double cadlculateTotalInMonth(final Month month) {
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




}
