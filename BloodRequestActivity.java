package com.anusha.coffee.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anusha.coffee.BloodRequest;
import com.anusha.coffee.R;
import com.anusha.coffee.databinding.ActivityBloodRequestBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BloodRequestActivity extends AppCompatActivity {

    private EditText etBloodType, etLocation, etContact, etQuantity;
    private Button btnSubmit;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ActivityBloodRequestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);

        etBloodType = findViewById(R.id.et_blood_type);
        etLocation = findViewById(R.id.et_location);
        etContact = findViewById(R.id.et_contact);
        etQuantity = findViewById(R.id.et_quantity);

        btnSubmit = findViewById(R.id.btn_submit);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("blood_requests");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bloodType = etBloodType.getText().toString().trim();
                String location = etLocation.getText().toString().trim();
                String contact = etContact.getText().toString().trim();
                String quantity = etQuantity.getText().toString().trim();

                if (bloodType.isEmpty() || location.isEmpty() || contact.isEmpty() || quantity.isEmpty()) {
                    Toast.makeText(BloodRequestActivity.this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
                } else {
                    BloodRequest bloodRequest = new BloodRequest(bloodType, location, contact, quantity, mAuth.getCurrentUser().getUid());

                    mDatabase.push().setValue(bloodRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(BloodRequestActivity.this, "Blood request submitted successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(BloodRequestActivity.this, "Failed to submit blood request", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}