package com.anusha.coffee.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anusha.coffee.R;
import com.anusha.coffee.databinding.ActivityDonateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DonateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText locationEditText;
    private Spinner bloodTypeSpinner;
    private Button donateButton;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private String selectedBloodType;
    ActivityDonateBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        locationEditText = findViewById(R.id.locationEditText);
        bloodTypeSpinner = findViewById(R.id.bloodTypeSpinner);
        donateButton = findViewById(R.id.donateButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(adapter);
        bloodTypeSpinner.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = locationEditText.getText().toString().trim();

                if (location.isEmpty()) {
                    Toast.makeText(DonateActivity.this, "Please enter a location", Toast.LENGTH_SHORT).show();
                } else {
                    String userId = mAuth.getCurrentUser().getUid();

                    Map<String, Object> donation = new HashMap<>();
                    donation.put("userId", userId);
                    donation.put("bloodType", selectedBloodType);
                    donation.put("location", location);

                    db.collection("donations").add(donation).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DonateActivity.this, "Donation request submitted", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(DonateActivity.this, "Error submitting donation request", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedBloodType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
}
