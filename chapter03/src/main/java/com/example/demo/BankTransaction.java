package com.example.demo;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {

    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date, final double amount, final String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Bank Transation {" + "...";
    }

    @Override
    public boolean equals(Object o) {
        //TODO: 생략
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
    }
}