package com.anusha.coffee.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anusha.coffee.R;
import com.anusha.coffee.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mProfileBtn;
    private Button mBloodBankBtn;
    private Button mDonateBtn;
    private Button mRequestBtn;

    private Button mChatBtn;

    private Button mChat1Btn;

    private Button mRegisterBtn;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            finish();
        }

        mProfileBtn = findViewById(R.id.profile_button);
        mBloodBankBtn = findViewById(R.id.blood_bank_button);
        mDonateBtn = findViewById(R.id.donate_button);
        mRequestBtn = findViewById(R.id.request_button);
        mChatBtn = findViewById(R.id.chat_button);
        mChat1Btn = findViewById(R.id.chat1_button);
//        mRegisterBtn = findViewById(R.id.register_btn);

        mProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

       mBloodBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BloodBankActivity.class));
            }
        });

        mDonateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DonateActivity.class));
            }
        });

        mRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BloodRequestActivity.class));
            }
        });

//        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
//            }
//        });
        mChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        mChat1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
            }
        });


    }
}
