package com.loanmanagement.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer creditScore;
}