package com.example.test.exception;

public class PaymentProcessingException extends RuntimeException {
    public PaymentProcessingException(String s) {
        super(s);
    }
}
