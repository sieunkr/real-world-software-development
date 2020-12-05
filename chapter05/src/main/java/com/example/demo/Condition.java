package com.example.demo;

@FunctionalInterface
public interface Condition {

    boolean evaluate(Facts facts);
}
