package com.example.project;

public class EmployeeAccount extends Account{

    public EmployeeAccount(){
    }
    public EmployeeAccount(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
