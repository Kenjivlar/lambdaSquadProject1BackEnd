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
    private long accountTypeId; // ID of the account type (role)
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private int creditScore;
}