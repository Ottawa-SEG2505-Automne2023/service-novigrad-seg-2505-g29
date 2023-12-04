package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class UserActionsActivity extends AppCompatActivity {

    private static final int REQUEST_SERVICE_MANAGER = 2;

    private Service selectedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_actions);
        Button buttonSubmitRequest = findViewById(R.id.buttonSubmitRequest);

        // Assuming you have some way of getting the selected service
        // Replace the following with your actual logic to get the selected service
        selectedService = getSelectedService();

        // Display service details in the form
        displayServiceDetails();

        buttonSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process the user's service request
                processServiceRequest();
            }
        });
    }

    // Replace this method with your actual logic to get the selected service
    private Service getSelectedService() {
        // Dummy implementation for illustration purposes
        // Replace this with your logic to get the selected service
        return new Service("Service Name", new ArrayList<>(), new ArrayList<>());
    }

    private void displayServiceDetails() {
        // Display service details in the form for the user to fill out
        EditText editTextServiceName = findViewById(R.id.editTextServiceName);
        editTextServiceName.setText(selectedService.getName());

        LinearLayout layoutFormFields = findViewById(R.id.linearLayoutFormFields);
        for (String formField : selectedService.getFormFields()) {
            addNewField(layoutFormFields, formField);
        }

        LinearLayout layoutDocuments = findViewById(R.id.linearLayoutDocuments);
        for (String document : selectedService.getRequiredDocuments()) {
            addNewField(layoutDocuments, document);
        }
    }

    private void addNewField(LinearLayout layout, String value) {
        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editText.setText(value);
        layout.addView(editText);
    }

    private void processServiceRequest() {
        // Retrieve user-entered data and process the service request
        String serviceName = ((EditText) findViewById(R.id.editTextServiceName)).getText().toString();
        java.util.List<String> formFieldValues = getFieldValues(findViewById(R.id.linearLayoutFormFields));
        java.util.List<String> documentValues = getFieldValues(findViewById(R.id.linearLayoutDocuments));

        if (serviceName.trim().isEmpty() || formFieldValues.isEmpty() || documentValues.isEmpty() || containsEmptyField(formFieldValues) || containsEmptyField(documentValues)) {
            // Show an error message if any of the fields is empty
            Toast.makeText(
                    UserActionsActivity.this,
                    "Veuillez remplir tous les champs du formulaire",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        // Process the service request (e.g., send to the server, store in the database, etc.)
        // For illustration purposes, let's show a Toast with the entered details
        Toast.makeText(
                UserActionsActivity.this,
                "Service Request Submitted Successfully\nName: " + serviceName + "\nForm Fields: " + formFieldValues + "\nDocuments: " + documentValues,
                Toast.LENGTH_LONG
        ).show();

        // You can also send the data to the server or store it in the database here

        finish(); // Finish the activity after processing the request
    }

    private List<String> getFieldValues(LinearLayout layout) {
        List<String> fieldValues = new ArrayList<>();

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof EditText) {
                String value = ((EditText) child).getText().toString();
                fieldValues.add(value);
            }
        }

        return fieldValues;
    }

    private boolean containsEmptyField(List<String> fieldValues) {
        for (String value : fieldValues) {
            if (value.isEmpty()) {
                return true; // There is an empty field
            }
        }
        return false; // All fields are non-empty
    }
}

