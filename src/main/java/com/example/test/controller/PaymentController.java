package com.example.test.controller;

import com.example.test.model.entity.Payment;
import com.example.test.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<String> handlePayRequest(
            @RequestBody Payment payment) {
        paymentService.savePayment(payment);
        return ResponseEntity.ok().body(payment.toString());
    }
}
