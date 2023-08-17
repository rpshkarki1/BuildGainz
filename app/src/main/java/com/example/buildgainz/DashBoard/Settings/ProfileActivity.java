package com.example.buildgainz.DashBoard.Settings;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildgainz.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String userId = getIntent().getStringExtra("user_id");
        String fullName = getIntent().getStringExtra("full_name");
        String email = getIntent().getStringExtra("email");

        if (userId != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users").child(userId);

            databaseReference.get().addOnSuccessListener(dataSnapshot -> {
                if (dataSnapshot.exists()) {
                    // Display user details
                    TextView fullNameTextView = findViewById(R.id.fullNameProfile);
                    TextView emailTextView = findViewById(R.id.yourEmailProfile);

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);

                    fullNameTextView.setTextColor(getResources().getColor(R.color.blue));
                    emailTextView.setTextColor(getResources().getColor(R.color.blue));
                    fullNameTextView.setEnabled(false);
                    emailTextView.setEnabled(false);
                }
            });

        }
    }
}