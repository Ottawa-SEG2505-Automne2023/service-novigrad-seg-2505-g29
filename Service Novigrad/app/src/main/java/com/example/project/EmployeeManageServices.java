package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class EmployeeManageServices extends AppCompatActivity {

    private ArrayList<Service> offeredServices = new ArrayList<>();

    private static final int REQUEST_ADD_SERVICE = 1;
    EmployeeAccount employee2;
    ArrayAdapter<Service>    offeredServicesAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manage_services);
        Intent intent = getIntent();

        // Assuming you have a ListView in your layout with the id "listViewOfferedServices"
        ListView listViewOfferedServices = findViewById(R.id.listViewServices);
        String email = intent.getStringExtra("EMAIL");
        offeredServices = intent.getParcelableArrayListExtra("OfferedServices");


        // Adapter for displaying offered services
        offeredServicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, offeredServices);
        listViewOfferedServices.setAdapter(offeredServicesAdapter);

        // Assuming you have a Button in your layout with the id "buttonAddService"
        Button buttonAddService = findViewById(R.id.buttonAddService);

        // Set up click listener for adding a service
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch activity to add a service
                Intent intent = new Intent(EmployeeManageServices.this, AddService.class);
                intent.putExtra("EMAIL", email);
                startActivityForResult(intent, REQUEST_ADD_SERVICE);
            }
        });

        DatabaseReference employeesRef = FirebaseDatabase.getInstance().getReference("Employee Accounts/");
        Query query = employeesRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EmployeeAccount employee = snapshot.getValue(EmployeeAccount.class);
                    if (employee != null) {
                        Toast.makeText(EmployeeManageServices.this, "Error: ", Toast.LENGTH_SHORT).show();
                        // Set the employee object here
                        employee2 = employee;

                        // Retrieve offered services from the employee object
                        offeredServices.clear();
                        offeredServices.addAll(employee.getOfferedServices());
                        // Notify the adapter
                        offeredServicesAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EmployeeManageServices.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        listViewOfferedServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle long click on an offered service
                showConfirmationDialog(position);
                return true; // Consume the long click event
            }
        });
    }

    // Handle the result from the AddServiceActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_SERVICE && resultCode == RESULT_OK && data != null) {
            // Handle the result from the AddServiceActivity
            Service addedService = data.getParcelableExtra("ADDED_SERVICE");

            if (addedService != null) {
                // Update the offered services list and notify the adapter
                offeredServices.add(addedService);

            }
        }
    }   private void showConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Do you want to delete this service?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed deletion, remove the service
                deleteOfferedService(position);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled deletion, do nothing
            }
        });
        builder.show();
    }private void deleteOfferedService(int position) {
        Service deletedService = offeredServices.get(position);
        offeredServices.remove(deletedService);
        DatabaseReference employeeRef = FirebaseDatabase.getInstance().getReference("Employee accounts/");

        // Find the specific employee by email
        Query query = employeeRef.orderByChild("email").equalTo(employee2.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Update the employee's offered services
                    employee2.removeOfferedService(deletedService);
                    // Update the employee data in the database
                    snapshot.getRef().setValue(employee2);
                    // Notify the user or perform additional actions if needed
                    Toast.makeText(EmployeeManageServices.this, "Service deleted: " + deletedService.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EmployeeManageServices.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        offeredServicesAdapter.notifyDataSetChanged();
    }
}
