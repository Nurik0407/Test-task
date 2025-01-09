package com.example.test.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;


public record PayRequest(
        @JacksonXmlProperty
        @NotNull(message = "Requisite cannot be null")
        UUID requisiteID,
        @JsonProperty
        @NotNull(message = "Payment amount cannot be null")
        @DecimalMin(value = "0.01", message = "The minimum payment amount must be greater than zero")
        BigDecimal amount
) {
}
