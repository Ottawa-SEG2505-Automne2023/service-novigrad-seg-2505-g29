// ViewRequestedService.java
package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ViewRequestedService extends AppCompatActivity {

    private static final int REQUEST_FILE_PICKER = 3;

    private ServiceRequest selectedRequest;
    private String employeeEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requested_service);

        Button buttonAccept = findViewById(R.id.buttonAccept);
        Button buttonDecline = findViewById(R.id.buttonDecline);

        selectedRequest = getIntent().getParcelableExtra("SELECTED_REQUEST");
        employeeEmail = getIntent().getStringExtra("EMAIL");

        displayServiceRequestDetails();

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processRequestResponse("Accepted");
            }
        });

        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processRequestResponse("Declined");
            }
        });
    }

    private void displayServiceRequestDetails() {
        // Display service request details
        TextView textViewServiceName = findViewById(R.id.textViewServiceName);
        textViewServiceName.setText(selectedRequest.getService().getName());

        LinearLayout layoutFormFields = findViewById(R.id.linearLayoutFormFields);
        for (String formFieldValue : selectedRequest.getFormFieldValues()) {
            addNewLabelAndValue(layoutFormFields, formFieldValue);
        }

        LinearLayout layoutDocuments = findViewById(R.id.linearLayoutDocuments);
        for (String documentValue : selectedRequest.getDocumentValues()) {
            addNewLabelAndValue(layoutDocuments, documentValue);
        }
    }

    private void addNewLabelAndValue(LinearLayout layout, String value) {
        // Create a new LinearLayout to hold the label and the value
        LinearLayout labelValueLayout = new LinearLayout(this);
        labelValueLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        labelValueLayout.setOrientation(LinearLayout.VERTICAL);

        // Create a TextView for the label
        TextView labelTextView = new TextView(this);
        labelTextView.setText(value);

        // Add the label to the labelValueLayout
        labelValueLayout.addView(labelTextView);

        // Add the labelValueLayout to the main layout
        layout.addView(labelValueLayout);
    }

    private void processRequestResponse(String response) {
        DatabaseReference serviceRequestsRef = FirebaseDatabase.getInstance().getReference("ServiceRequests");

        serviceRequestsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ServiceRequest serviceRequest = snapshot.getValue(ServiceRequest.class);

                    // Check if the service request matches the selected service and employee
                    if (serviceRequest != null && serviceRequest.equals(selectedRequest)) {
                        // Update the status of the service request directly in the database
                        snapshot.getRef().child("status").setValue(response);

                        // Show a success message
                        Toast.makeText(
                                ViewRequestedService.this,
                                "Request " + response + " Successfully",
                                Toast.LENGTH_LONG
                        ).show();

                        // Finish the activity after processing the request
                        finish();
                        return; // Exit the loop if a matching service request is found
                    }
                }

                // Handle the case where no matching service request is found
                Toast.makeText(
                        ViewRequestedService.this,
                        "Matching service request not found",
                        Toast.LENGTH_LONG
                ).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
                Toast.makeText(
                        ViewRequestedService.this,
                        "Error: " + databaseError.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }


}

