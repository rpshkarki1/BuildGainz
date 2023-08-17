package com.example.buildgainz.LoginPage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildgainz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;

    private final static String TAG = "ForgotPasswordActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Button continueBtn = findViewById(R.id.continueButton);
        emailEditText = findViewById(R.id.editTextEmail);
        ImageButton backBtn = findViewById(R.id.backButton);
        backBtn.setOnClickListener(v -> startActivity(new Intent(ForgotPasswordActivity.this, LoginPageActivity.class)));

        continueBtn.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter your registered email!", Toast.LENGTH_SHORT).show();
                emailEditText.setError("Email is required.");
                emailEditText.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter your valid email.", Toast.LENGTH_SHORT).show();
                emailEditText.setError("Valid Email is required.");
                emailEditText.requestFocus();
            } else {
                resetPassword(email);
            }

        });


    }

    private void resetPassword(String email) {
        FirebaseAuth authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ForgotPasswordActivity.this, "Check your Inbox for password reset link!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginPageActivity.class);

                //prevent user from returning back to registerActivity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {

                try {
                    throw Objects.requireNonNull(task.getException());

                } catch (FirebaseAuthInvalidUserException e) {
                    emailEditText.setError("User does not exists or is no longer available.");
                } catch (Exception e){
                    Log.e(TAG, Objects.requireNonNull(e.getMessage()));

                    Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}