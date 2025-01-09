package com.example.test.service.impl;

import com.example.test.exception.CheckValidateException;
import com.example.test.exception.NotFoundException;
import com.example.test.exception.PaymentProcessingException;
import com.example.test.model.dto.CheckRequest;
import com.example.test.model.dto.CheckResponse;
import com.example.test.model.dto.PayRequest;
import com.example.test.model.dto.PayResponse;
import com.example.test.model.entity.Payment;
import com.example.test.model.entity.Requisite;
import com.example.test.model.enums.PaymentStatus;
import com.example.test.repository.PaymentRepository;
import com.example.test.repository.RequisiteRepository;
import com.example.test.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RequisiteRepository requisiteRepository;
    public static final BigDecimal MIN_PAYMENT_AMOUNT = BigDecimal.ZERO;
    private static final BigDecimal MAX_TRANSFER_LIMIT = new BigDecimal("1000000");


    @Override
    public PayResponse savePayment(PayRequest request) throws PaymentProcessingException, CheckValidateException {
        log.info("Processing of the request to create a payment has begun: {}", request);

        validateAmount(request.amount());

        Requisite requisite = requisiteRepository.findById(request.requisiteID())
                .orElseThrow(() -> {
                    log.error("Attribute with ID = {} not found", request.requisiteID());
                    return new NotFoundException("Requisite not found!");
                });

        Payment payment = new Payment();
        payment.setAmount(request.amount());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setRequisite(requisite);

        try {
            Payment savedPayment = paymentRepository.save(payment);
            log.info("Payment successfully saved: ID = {}", savedPayment.getId());
            return new PayResponse(
                    savedPayment.getId(),
                    savedPayment.getStatus(),
                    "The payment was successfully processed."
            );
        } catch (Exception e) {
            log.error("Error when saving payment: {}", e.getMessage(), e);
            throw new PaymentProcessingException("An error occurred while processing the payment. Please try again later.");
        }
    }

    @Override
    public CheckResponse check(CheckRequest request) throws CheckValidateException {
        log.info("Validation of details has started: ID = {}, amount= {}", request.requisiteID(), request.amount());

        validateAmount(request.amount());

        Requisite requisite = requisiteRepository.findById(request.requisiteID())
                .orElseThrow(() -> {
                    log.error("Attribute with ID = {} not found", request.requisiteID());
                    return new NotFoundException("Requisite not found!");
                });

        log.info("Requisite found: ID = {}, active = {}, max. sum = {}",
                requisite.getId(), requisite.isActive(), requisite.getMaxSum());

        if (!requisite.isActive()) {
            log.warn("Attribute with ID = {} is not available", request.requisiteID());
            return new CheckResponse("Requisite not available");
        }

        if (requisite.getMaxSum().compareTo(request.amount()) > -1) {
            log.info("The details are available for the specified amount: {}", request.amount());
            return new CheckResponse("Requisite available.");
        } else {
            log.warn("The maximum amount for the props has been exceeded: max. = {}, requested amount = {}",
                    requisite.getMaxSum(), request.amount());
            return new CheckResponse("The maximum amount for the prop has been exceeded.");
        }
    }

    private void validateAmount(BigDecimal amount) throws CheckValidateException {
        if (amount.compareTo(MIN_PAYMENT_AMOUNT) <= 0) {
            log.error("Payment processing error: incorrect amount({})", amount);
            throw new CheckValidateException("The payment amount must be positive.");
        }

        if (amount.compareTo(MAX_TRANSFER_LIMIT) > 0) {
            log.warn("Global transfer limit exceeded: max. = {}, requested = {}", MAX_TRANSFER_LIMIT, amount);
            throw new CheckValidateException("The amount exceeds the allowable transfer limit.");
        }
    }
}
