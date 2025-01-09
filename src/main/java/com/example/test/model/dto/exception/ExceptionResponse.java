package com.example.test.model.dto.exception;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.http.HttpStatus;

@JacksonXmlRootElement
public record ExceptionResponse(
        @JacksonXmlProperty
        HttpStatus httpStatus,
        @JacksonXmlProperty
        String exceptionClassName,
        @JacksonXmlProperty
        String exceptionMessage
) {
}
