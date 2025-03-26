package com.loanmanagement.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class UpdateLoanStatusDTO {
    @NotNull()
    private String status;
}
