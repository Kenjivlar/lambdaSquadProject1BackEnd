package com.loanmanagement.advice;

import lombok.AllArgsConstructor;

public class IlegalRequestException extends RuntimeException {
    public IlegalRequestException(String message) {
        super(message);
    }
}