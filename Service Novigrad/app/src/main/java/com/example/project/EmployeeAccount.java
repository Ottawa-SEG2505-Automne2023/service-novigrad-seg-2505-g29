package com.example.project;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAccount extends Account{

    List<Service> offeredServices;

    public EmployeeAccount(){
    }
    public EmployeeAccount(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        offeredServices = new ArrayList<>();
    }

    // Add a service to the offered services
    public void addOfferedService(Service service) {
        offeredServices.add(service);
    }

    // Remove a service from the offered services
    public void removeOfferedService(Service service) {
        offeredServices.remove(service);
    }

    // Get the list of offered services
    public List<Service> getOfferedServices() {
        return offeredServices;
    }

}
