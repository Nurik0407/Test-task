package com.example.test.service;

import com.example.test.exception.CheckValidateException;
import com.example.test.exception.PaymentProcessingException;
import com.example.test.model.dto.CheckRequest;
import com.example.test.model.dto.CheckResponse;
import com.example.test.model.dto.PayRequest;
import com.example.test.model.dto.PayResponse;

public interface PaymentService {
    PayResponse savePayment(PayRequest request) throws PaymentProcessingException, CheckValidateException;

    CheckResponse check(CheckRequest request) throws CheckValidateException;
}
