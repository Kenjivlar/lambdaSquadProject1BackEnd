package com.loanmanagement.advice;

import lombok.AllArgsConstructor;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}