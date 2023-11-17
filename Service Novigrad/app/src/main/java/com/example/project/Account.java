package com.example.project;

public abstract class Account {

    String firstName;
    String lastName;
    String email;
    String password;
    int accountType; // 0 for user, 1 for employee/branch, 2 for admin


    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    void setLastName(String lastName) {
        this.lastName = lastName;
    }
    void setEmail(String email) {
        this.email = email;
    }
    void setPassword(String password) {
        this.password = password;
    }
    void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public int getAccountType() {
        return accountType;
    }
}


