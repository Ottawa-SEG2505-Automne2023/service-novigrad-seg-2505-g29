package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
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
                            servicesAdapter.add(service.getName() + "           Rating : " + service.getRating());
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

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Show a dialog for rating the service
                showRatingDialog(offeredServices.get(position));
                return true; // Consume the long click event
            }
        });


    }

    private void showRatingDialog (Service selectedService){
        // Create a custom layout for the dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_rating, null);

        // Find the NumberPicker in the layout
        NumberPicker numberPickerRating = dialogLayout.findViewById(R.id.numberPickerRating);

        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogLayout);
        builder.setTitle("Rate the service");

        // Set positive button
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the selected rating
                int rating = numberPickerRating.getValue();
                selectedService.addRating(rating);

                // Perform any action with the rating, such as saving it to the database
                // For now, let's just show a Toast with the rating
                Toast.makeText(EmployeeServicesActivity.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        // Set negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog without doing anything
            }
        });

        // Show the dialog
        builder.show();
    }
}
