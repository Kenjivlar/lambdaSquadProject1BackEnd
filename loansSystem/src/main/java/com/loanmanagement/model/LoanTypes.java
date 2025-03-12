package com.loanmanagement.model;

public class LoanTypes {
    private int id;
    private String loanType;

    public LoanTypes() {
    }

    public LoanTypes(String loanType) {
        this.loanType = loanType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
}