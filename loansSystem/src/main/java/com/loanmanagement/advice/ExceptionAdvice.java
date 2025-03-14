package com.loanmanagement.advice;

import com.loanmanagement.dto.ErrorsDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ErrorsDTO handleNotFoundException(ResponseStatusException ex) {
        List<String> errors = new LinkedList<>();
        errors.add(ex.getReason());

        return new ErrorsDTO("NOT_FOUND", "The requested resource was not found", errors);
    }

    @ExceptionHandler(IlegalRequestException.class)
    public ErrorsDTO handleIlegalRequestException(ResponseStatusException ex) {
        List<String> errors = new LinkedList<>();
        errors.add(ex.getReason());

        return new ErrorsDTO("BAD_REQUEST", "The request was not valid", errors);
    }
}