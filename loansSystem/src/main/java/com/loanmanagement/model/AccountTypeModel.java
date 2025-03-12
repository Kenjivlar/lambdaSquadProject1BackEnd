package com.loanmanagement.model;

public class AccountTypeModel {
    private int id;
    private String typeName;

    public AccountTypeModel() {
    }

    public AccountTypeModel(String typeName) {

        this.typeName = typeName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}