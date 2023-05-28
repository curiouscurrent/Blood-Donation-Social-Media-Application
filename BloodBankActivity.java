package com.anusha.coffee.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anusha.coffee.Adapters.BloodBankAdapter;
import com.anusha.coffee.BloodBank;
import com.anusha.coffee.R;
import com.anusha.coffee.databinding.ActivityBloodBankBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BloodBankActivity extends AppCompatActivity {

    private ListView listView;
    private FirebaseFirestore db;

    ActivityBloodBankBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);

        listView = findViewById(R.id.listView);
        db = FirebaseFirestore.getInstance();

        // Retrieve list of blood banks from Firestore
        // Retrieve list of blood banks from Firestore
        db.collection("bloodbanks").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<BloodBank> bloodBanks = new ArrayList<>();
                            for (DocumentSnapshot document : task.getResult()) {
                                String name = document.getString("name");
                                String location = document.getString("location");
                                List<String> availableBloodTypes = (List<String>) document.get("available_blood_types");

                                BloodBank bloodBank = new BloodBank(name, location, availableBloodTypes);
                                bloodBanks.add(bloodBank);
                            }
                            BloodBankAdapter adapter = new BloodBankAdapter(BloodBankActivity.this, bloodBanks);
                            listView.setAdapter(adapter);
                        } else {
                            Log.d("BloodBankActivity", "Error getting blood banks: ", task.getException());
                        }
                    }
                });



    }
}





