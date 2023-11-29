package com.example.project;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeAccount extends Account{

    public String adresse;
    public int phoneNumber;
    public String mondayHours;
    public String tuesdayHours;
    public String wednesdayHours;
    public String thursdayHours;
    public String fridayHours;
    public String saturdayHours;
    public String sundayHours;
    List<Service> offeredServices;


    public EmployeeAccount(){
    }
    public EmployeeAccount(String firstName, String lastName, String email, String password, List<Service> arrayList, String adresse, int phoneNumber, String mondayHours, String tuesdayHours, String wednesdayHours, String thursdayHours,
                           String fridayHours, String saturdayHours, String sundayHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.offeredServices = new ArrayList<>();
        this.offeredServices.add(new Service("Default",new ArrayList<String>(), new ArrayList<String>()));
       this.adresse = adresse;
        this.phoneNumber = phoneNumber;
        this.mondayHours = mondayHours;
        this.tuesdayHours = tuesdayHours;
        this.wednesdayHours = wednesdayHours;
        this.thursdayHours = thursdayHours;
        this.fridayHours = fridayHours;
        this.saturdayHours = saturdayHours;
        this.sundayHours = sundayHours;
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


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("email", email);
        result.put("password", password);
        result.put("offeredServices", offeredServices); // Assuming Service class is Serializable or Parcelable

        return result;
    }

}
