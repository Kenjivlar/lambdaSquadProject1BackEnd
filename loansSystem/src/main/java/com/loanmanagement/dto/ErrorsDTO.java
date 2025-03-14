package com.loanmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class ErrorsDTO {
    private String code;
    private String message;
    private Object details;
}