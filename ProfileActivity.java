package com.anusha.coffee.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anusha.coffee.R;
import com.anusha.coffee.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText bloodTypeEditText;
    private EditText locationEditText;
    private Button saveButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());

        nameEditText = findViewById(R.id.nameEditText);
        bloodTypeEditText = findViewById(R.id.bloodTypeEditText);
        locationEditText = findViewById(R.id.locationEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });

        // Populate user's profile data
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.hasChild("name") ? dataSnapshot.child("name").getValue().toString() : "";
                String bloodType = dataSnapshot.hasChild("bloodType") ? dataSnapshot.child("bloodType").getValue().toString() : "";
                String location = dataSnapshot.hasChild("location") ? dataSnapshot.child("location").getValue().toString() : "";


                nameEditText.setText(name);
                bloodTypeEditText.setText(bloodType);
                locationEditText.setText(location);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveProfile() {
        String name = nameEditText.getText().toString().trim();
        String bloodType = bloodTypeEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Name is required");
            nameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(bloodType)) {
            bloodTypeEditText.setError("Blood type is required");
            bloodTypeEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(location)) {
            locationEditText.setError("Location is required");
            locationEditText.requestFocus();
            return;
        }

        // Apply the span if the text is not empty
        if (!TextUtils.isEmpty(name)) {
            SpannableString spannableString = new SpannableString(name);
            // Apply your span here
            nameEditText.setText(spannableString);
        }

        HashMap<String, Object> profileMap = new HashMap<>();
        profileMap.put("name", name);
        profileMap.put("bloodType", bloodType);
        profileMap.put("location", location);

        mDatabase.updateChildren(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}