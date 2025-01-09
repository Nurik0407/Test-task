package com.example.test.controller;

import com.example.test.exception.CheckValidateException;
import com.example.test.exception.PaymentProcessingException;
import com.example.test.model.dto.CheckRequest;
import com.example.test.model.dto.CheckResponse;
import com.example.test.model.dto.PayRequest;
import com.example.test.model.dto.PayResponse;
import com.example.test.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки платежей и проверки реквизитов.
 */
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Обрабатывает запрос на создание платежа.
     *
     * @param payRequest запрос с данными платежа.
     * @return объект PayResponse с результатом обработки платежа.
     * @throws PaymentProcessingException если произошла ошибка при обработке платежа.
     * @throws CheckValidateException     если произошла ошибка при проверке суммы.
     */
    @PostMapping(value = "/pay", consumes = {"application/json"}, produces = {"application/xml"})
    public ResponseEntity<PayResponse> processPayment(@RequestBody @Valid PayRequest payRequest) throws PaymentProcessingException, CheckValidateException {
        PayResponse payResponse = paymentService.savePayment(payRequest);
        return ResponseEntity.ok().body(payResponse);
    }

    /**
     * Обрабатывает запрос на проверку состояния реквизита.
     *
     * @param checkRequest запрос с данными для проверки реквизита.
     * @return объект CheckResponse с результатом проверки реквизита.
     * @throws CheckValidateException если произошла ошибка при проверке суммы для платежа.
     */
    @PostMapping(value = "/check", consumes = {"application/json"}, produces = {"application/xml"})
    public ResponseEntity<CheckResponse> checkPayment(@RequestBody @Valid CheckRequest checkRequest) throws CheckValidateException {
        return ResponseEntity.ok().body(paymentService.check(checkRequest));
    }
}
