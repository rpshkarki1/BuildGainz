package com.example.buildgainz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        Button btnGetStart;

        btnGetStart = findViewById(R.id.getStartedButton);

        btnGetStart.setOnClickListener(v -> {
            Intent iGetStart ;
            iGetStart = new Intent(GetStartedActivity.this, LoginPageActivity.class);
            startActivity(iGetStart);
            finish();
        });


    }
}