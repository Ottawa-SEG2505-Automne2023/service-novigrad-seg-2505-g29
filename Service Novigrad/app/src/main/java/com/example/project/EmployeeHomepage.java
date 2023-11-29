package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_homepage);

        //Variables
        Button btnManageServices = findViewById(R.id.btnManageServices);
        Button btnManageBranchProfile = findViewById(R.id.btnManageBranchProfile);

        //ouvre la page pour ajouter un service
        btnManageServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeHomepage.this, EmployeeManageServices.class));
            }
        });
    }
}