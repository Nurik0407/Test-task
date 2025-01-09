package com.example.test.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@JacksonXmlRootElement
public record CheckRequest(
        @JacksonXmlProperty
        @NotNull(message = "Requisite cannot be null")
        UUID requisiteID,
        @JacksonXmlProperty
        @NotNull(message = "Payment amount cannot be null")
        @DecimalMin(value = "0.01", message = "The minimum payment amount must be greater than zero")
        BigDecimal amount
) {
}
