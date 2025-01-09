package com.example.test.service.impl;

import com.example.test.model.entity.Payment;
import com.example.test.repository.PaymentRepository;
import com.example.test.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void savePayment(Payment payment) {
        log.info("savePayment");
        paymentRepository.save(payment);
    }
}
