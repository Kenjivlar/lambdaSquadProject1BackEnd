package com.loanmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationRequest {
    private Long loanTypeId;
    private BigDecimal interestRate;
    private BigDecimal amount;
    private String title;
    private String description;
}