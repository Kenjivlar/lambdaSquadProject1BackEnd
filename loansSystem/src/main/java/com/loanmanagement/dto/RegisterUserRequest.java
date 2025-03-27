package com.loanmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String email;
    private String password;
    private long accountTypeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int creditScore;
}