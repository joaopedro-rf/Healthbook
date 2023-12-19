package com.example.Healthbook.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails{
    private String fields;
    private String fieldsMessage;

}
