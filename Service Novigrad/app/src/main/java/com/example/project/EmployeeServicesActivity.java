package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServicesActivity extends AppCompatActivity {

    private List<Service> offeredServices = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_services);

        // Assuming you have a ListView in your layout with the id "listViewServices"
        ListView listViewServices = findViewById(R.id.listViewServices);

        // Adapter for displaying service names
        ArrayAdapter<String> servicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewServices.setAdapter(servicesAdapter);

        // Get the email of the selected employee from the intent
        Intent intent = getIntent();
        String employeeEmail = intent.getStringExtra("EMPLOYEE_EMAIL");
        // Retrieve offered services from Firebase
        DatabaseReference employeeRef = FirebaseDatabase.getInstance().getReference("Employee accounts/");
        Query query = employeeRef.orderByChild("email").equalTo(employeeEmail);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    EmployeeAccount employee = snapshot.getValue(EmployeeAccount.class);
                    if (employee != null) {
                        // Set the offered services here
                        offeredServices = employee.getOfferedServices();

                        // Populate the ListView with service names
                        for (Service service : offeredServices) {
                            servicesAdapter.add(service.getName());
                        }

                        // Notify the adapter
                        servicesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EmployeeServicesActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Handle item click on the ListView (service selected)
        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create an intent to start the UserActionServiceActivity
                Intent intent = new Intent(EmployeeServicesActivity.this, UserActionsActivity.class);
                // Pass the selected service to UserActionServiceActivity
                intent.putExtra("SELECTED_SERVICE", offeredServices.get(position));
                intent.putExtra("EMPLOYEE_EMAIL", employeeEmail);
                startActivity(intent);
            }
        });
    }
}

