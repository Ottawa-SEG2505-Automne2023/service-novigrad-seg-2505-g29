package com.example.project;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class ExampleUnitTest {

    @Test
    public void checkServiceName() {
        Service service = new Service("test",new ArrayList<String>(), new ArrayList<String>());
        assertEquals("test", service.getName());
    }
    @Test
    public void checkServiceFormFields() {
        ArrayList<String> formFields = new ArrayList<String>();
        formFields.add("First Name :");
        formFields.add("Last Name :");
        formFields.add("Phone Number :");
        formFields.add("Adress :");
        Service service = new Service("",formFields, new ArrayList<String>());
        assertEquals(formFields, service.getFormFields());
    }

    @Test
    public void checkServiceDocuments() {
        ArrayList<String> docs = new ArrayList<String>();
        docs.add("Driver License");
        Service service = new Service("",new ArrayList<String>(), docs);
        assertEquals(docs, service.getRequiredDocuments());
    }
    @Test
    public void  checkServiceFormFieldsSetters() {
        ArrayList<String> formFields = new ArrayList<String>();
        formFields.add("First Name :");
        formFields.add("Last Name :");
        formFields.add("Phone Number :");
        formFields.add("Adress :");
        Service service = new Service("",new ArrayList<String>(), new ArrayList<String>());
        service.setFormFields(formFields);
        assertEquals(formFields, service.getFormFields());
    }

    @Test
    public void  checkServiceDocumentSetters() {
        ArrayList<String> docs = new ArrayList<String>();
        docs.add("Driver License");
        Service service = new Service("",new ArrayList<String>(), new ArrayList<String>());
        service.setRequiredDocuments(docs);
        assertEquals(docs, service.getRequiredDocuments());
    }

    @Test
    public void CheckOfferedServicesGetter()
    {
        Service service = new Service("a",new ArrayList<String>(), new ArrayList<String>());
        ArrayList<Service> services = new ArrayList<>();
        services.add(service);
        EmployeeAccount employee = new EmployeeAccount("1","2","3","4",new ArrayList<>());
        employee.addOfferedService(service);
        assertEquals(services, employee.getOfferedServices());
    }

    public void ServiceRatingGetter()
    {
        Service service = new Service("a",new ArrayList<String>(), new ArrayList<String>());
        service.addRating(5);
        service.addRating(3);
        assertEquals(service.getRating(),4);
    }

    @Test
    public void CheckOfferedServicesRemover()
    {
        Service service = new Service("a",new ArrayList<String>(), new ArrayList<String>());
        ArrayList<Service> services = new ArrayList<>();
        EmployeeAccount employee = new EmployeeAccount("1","2","3","4",new ArrayList<>());
        employee.addOfferedService(service);
        employee.removeOfferedService(service);
        assertEquals(services, employee.getOfferedServices());
    }
}