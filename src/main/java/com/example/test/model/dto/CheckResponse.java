package com.example.test.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Response")
public record CheckResponse(
        @JacksonXmlProperty(localName = "Message")
        String message
) {
}
