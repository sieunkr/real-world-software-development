package com.example.demo;

public interface ConditionalAction {
    void perform(Facts facts);
    boolean evaluate(Facts facts);
}
