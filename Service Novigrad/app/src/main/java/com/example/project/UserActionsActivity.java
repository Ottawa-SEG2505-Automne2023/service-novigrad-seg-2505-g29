package com.example.project;

// UserActionsActivity.java
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
// (Existing imports...)

public class UserActionsActivity extends AppCompatActivity {

    private static final int REQUEST_FILE_PICKER = 3;

    private Service selectedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_actions);

        Button buttonSubmitRequest = findViewById(R.id.buttonSubmitRequest);

        // Assuming you have some way of getting the selected service
        // Replace the following with your actual logic to get the selected service
        selectedService = getIntent().getParcelableExtra("SELECTED_SERVICE");

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

    private void displayServiceDetails() {
        // Display service details in the form for the user to fill out
        EditText editTextServiceName = findViewById(R.id.editTextServiceName);
        editTextServiceName.setText(selectedService.getName());

        LinearLayout layoutFormFields = findViewById(R.id.linearLayoutFormFields);
        for (String formFieldLabel : selectedService.getFormFields()) {
            addNewField(layoutFormFields, formFieldLabel);
        }

        LinearLayout layoutDocuments = findViewById(R.id.linearLayoutDocuments);
        for (String document : selectedService.getRequiredDocuments()) {
            addNewDocumentField(layoutDocuments, document);
        }
    }

    private void addNewField(LinearLayout layout, String label) {
        // Create a new LinearLayout to hold the label and the user input field
        LinearLayout fieldLayout = new LinearLayout(this);
        fieldLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        fieldLayout.setOrientation(LinearLayout.VERTICAL);

        // Create a TextView for the label
        TextView labelTextView = new TextView(this);
        labelTextView.setText(label);

        // Create an EditText for user input
        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Add the label and EditText to the fieldLayout
        fieldLayout.addView(labelTextView);
        fieldLayout.addView(editText);

        // Add the fieldLayout to the main layout
        layout.addView(fieldLayout);
    }

    private void addNewDocumentField(LinearLayout layout, String label) {
        // Create a new LinearLayout to hold the label and the document selection button
        LinearLayout fieldLayout = new LinearLayout(this);
        fieldLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        fieldLayout.setOrientation(LinearLayout.VERTICAL);

        // Create a TextView for the label
        TextView labelTextView = new TextView(this);
        labelTextView.setText(label);

        // Create a Button for document selection
        Button selectDocumentButton = new Button(this);
        selectDocumentButton.setText("Select Document");
        selectDocumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the file picker when the button is clicked
                openFilePicker();
            }
        });

        // Add the label and document selection button to the fieldLayout
        fieldLayout.addView(labelTextView);
        fieldLayout.addView(selectDocumentButton);

        // Add the fieldLayout to the main layout
        layout.addView(fieldLayout);
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*"); // Allow all file types
        startActivityForResult(intent, REQUEST_FILE_PICKER);
    }

    private void processServiceRequest() {
        // Retrieve user-entered data and process the service request
        String serviceName = ((EditText) findViewById(R.id.editTextServiceName)).getText().toString();
        List<String> formFieldValues = getFieldValues(findViewById(R.id.linearLayoutFormFields));
        List<String> documentValues = getFieldValues(findViewById(R.id.linearLayoutDocuments));

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
            if (child instanceof LinearLayout) {
                LinearLayout fieldLayout = (LinearLayout) child;
                View innerChild = fieldLayout.getChildAt(1); // Assuming the EditText or Button is the second child
                if (innerChild instanceof EditText) {
                    String value = ((EditText) innerChild).getText().toString();
                    fieldValues.add(value);
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FILE_PICKER && resultCode == RESULT_OK && data != null) {
            // Handle the selected document here
            Uri selectedDocumentUri = data.getData();
            // You can use the selectedDocumentUri to get the file path or perform other operations
            // For now, let's just show a Toast with the selected document URI
            Toast.makeText(this, "Selected Document: " + selectedDocumentUri.toString(), Toast.LENGTH_LONG).show();
        }
    }
}





