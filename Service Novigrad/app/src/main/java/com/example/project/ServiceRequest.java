package com.example.project;

// ServiceRequest.java

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceRequest implements Parcelable {
    private String userName;
    private String userAddress;
    private String userDocuments;
    private Service selectedService;

    // Default constructor
    public ServiceRequest() {
        // Default constructor required for calls to DataSnapshot.getValue(ServiceRequest.class)
    }

    // Parameterized constructor
    public ServiceRequest(String userName, String userAddress, String userDocuments, Service selectedService) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userDocuments = userDocuments;
        this.selectedService = selectedService;
    }

    // Getter methods
    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public Service getSelectedService() {
        return selectedService;
    }

    // Parcelable implementation
    protected ServiceRequest(Parcel in) {
        userName = in.readString();
        userAddress = in.readString();
        userDocuments = in.readString();
        selectedService = in.readParcelable(Service.class.getClassLoader());
    }

    public static final Creator<ServiceRequest> CREATOR = new Creator<ServiceRequest>() {
        @Override
        public ServiceRequest createFromParcel(Parcel in) {
            return new ServiceRequest(in);
        }

        @Override
        public ServiceRequest[] newArray(int size) {
            return new ServiceRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userAddress);
        dest.writeString(userDocuments);
        dest.writeParcelable(selectedService, flags);
    }
}

