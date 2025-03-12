package com.loanmanagement.model;

public class LoanApplicationsModel {
    private int id;
    private int idUser;
    private int idLoanType;
    private int statusID;
    private double amountRequested;

    public LoanApplicationsModel() {
    }

    public LoanApplicationsModel(int userID, int loanType, int statusID, double amountRequested) {
        this.idUser = userID;
        this.idLoanType = loanType;
        this.statusID = statusID;
        this.amountRequested = amountRequested;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return idUser;
    }

    public void setUserID(int userID) {
        this.idUser = userID;
    }

    public int getLoanType() {
        return idLoanType;
    }

    public void setLoanType(int loanType) {
        this.idLoanType = loanType;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public double getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(double amountRequested) {
        this.amountRequested = amountRequested;
    }
}