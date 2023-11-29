package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_homepage);
        Intent intent = getIntent();
        //Variables
        Button btnManageServices = findViewById(R.id.btnManageServices);
        Button btnManageBranchProfile = findViewById(R.id.btnManageBranchProfile);
        String email = intent.getStringExtra("EMAIL");
        ArrayList<Service> offeredServices = intent.getParcelableArrayListExtra("OfferedServices");
        //ouvre la page pour ajouter un service
        btnManageServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeHomepage.this, EmployeeManageServices.class);
                intent.putExtra("EMAIL", email);
                intent.putParcelableArrayListExtra("OfferedServices", offeredServices);
                startActivity(intent);
            }
        });
    }
}