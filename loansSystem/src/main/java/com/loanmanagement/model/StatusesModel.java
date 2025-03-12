package com.loanmanagement.model;

public class StatusesModel {
    private int id;
    private String status;

    public StatusesModel() {
    }

    public StatusesModel(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}