package com.example.demo;

import java.time.Month;

//TODO: 새로운 요구사항이 있을때마다 별도의 클래스를 만들어야 한다.. 람다 표현식으로 변경 가능.
public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY &&
                bankTransaction.getAmount() >= 1_000;
    }
}
