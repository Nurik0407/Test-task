package com.example.test.model.dto;

import com.example.test.model.enums.PaymentStatus;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.UUID;

@JacksonXmlRootElement(localName = "Response")
public record PayResponse(
        @JacksonXmlProperty(localName = "PayID")
        UUID payId,
        @JacksonXmlProperty(localName = "Status")
        PaymentStatus status,
        @JacksonXmlProperty(localName = "Message")
        String message
) {
}
