package com.example.demo;

@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}