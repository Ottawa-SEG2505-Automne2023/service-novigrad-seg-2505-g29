package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EmployeeHomePage extends AppCompatActivity {

    private ArrayList<Service> offeredServices = new ArrayList<>(); // Assuming you have this ArrayList
    private ArrayList<Service> availableServices = new ArrayList<>(); // Assuming you fetch this from Firebase

    private static final int REQUEST_ADD_SERVICE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_homepage);

        // Assuming you have a ListView in your layout with the id "listViewOfferedServices"
        ListView listViewOfferedServices = findViewById(R.id.listViewServices);

        // Adapter for displaying offered services
        ArrayAdapter<Service> offeredServicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, offeredServices);
        listViewOfferedServices.setAdapter(offeredServicesAdapter);

        // Assuming you have a Button in your layout with the id "buttonAddService"
        Button buttonAddService = findViewById(R.id.buttonAddService);

        // Set up click listener for adding a service
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch activity to add a service
                Intent intent = new Intent(EmployeeHomePage.this, AddService.class);
                intent.putExtra("AVAILABLE_SERVICES", availableServices);
                startActivityForResult(intent, REQUEST_ADD_SERVICE);
            }
        });

        // Set up item click listener for the offered services
        listViewOfferedServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle click on an offered service if needed
                Service selectedService = offeredServices.get(position);
                Toast.makeText(EmployeeHomePage.this, "Clicked on: " + selectedService.getName(), Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<Service> adapter = (ArrayAdapter<Service>) ((ListView) findViewById(R.id.listViewServices)).getAdapter();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}