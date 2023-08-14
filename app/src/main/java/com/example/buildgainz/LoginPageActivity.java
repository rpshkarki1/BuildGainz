package com.example.buildgainz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPageActivity extends AppCompatActivity {

    TextView signUp, forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //When Clicked Sign In Button

        Button buttonSignIn = findViewById(R.id.signInBtn);
        buttonSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPageActivity.this, DashBoardActivity.class);
            startActivity(intent);
        });

        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPageActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        forgotPass = findViewById(R.id.forgot_pass);
        forgotPass.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPageActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });


    }
}