package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAccount extends Account{

    List<Service> OfferedServices;
    public EmployeeAccount(){
    }
    public EmployeeAccount(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        OfferedServices = new ArrayList<>();
    }
}
