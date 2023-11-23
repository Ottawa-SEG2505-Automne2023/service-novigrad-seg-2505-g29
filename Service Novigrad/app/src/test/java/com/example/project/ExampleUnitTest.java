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
}