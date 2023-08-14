package com.example.buildgainz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullName, yourEmail, yourPassword, reEnterPassword;

    private final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toast.makeText(SignUpActivity.this, "You can Sign Up now ", Toast.LENGTH_LONG).show();


        fullName = findViewById(R.id.fullName);
        yourEmail = findViewById(R.id.yourEmail);
        yourPassword = findViewById(R.id.enterPass);
        reEnterPassword = findViewById(R.id.reEnterPass);
        TextView signInNow = findViewById(R.id.signInNow);
        Button signUp = findViewById(R.id.signUpAccBtn);

        signInNow.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginPageActivity.class);
            startActivity(intent);
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFullName = fullName.getText().toString();
                String textYourEmail = yourEmail.getText().toString();
                String textYourPassword = yourPassword.getText().toString();
                String textReEnterPassword = reEnterPassword.getText().toString();

                if (TextUtils.isEmpty(textFullName)) {
                    Toast.makeText(SignUpActivity.this, "Please Enter your Full Name", Toast.LENGTH_LONG).show();
                    fullName.setError("Full Name is required");
                    fullName.requestFocus();
                } else if (TextUtils.isEmpty(textYourEmail)) {
                    Toast.makeText(SignUpActivity.this, "Please Enter your email", Toast.LENGTH_LONG).show();
                    yourEmail.setError("Email is required");
                    yourEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textYourEmail).matches()) {
                    Toast.makeText(SignUpActivity.this, "Please re-Enter your email", Toast.LENGTH_LONG).show();
                    yourEmail.setError("Valid Email is required");
                    yourEmail.requestFocus();

                } else if (TextUtils.isEmpty(textYourPassword)) {
                    Toast.makeText(SignUpActivity.this, "Please Enter your password", Toast.LENGTH_LONG).show();
                    yourPassword.setError("Password is required");
                    yourPassword.requestFocus();
                } else if (textYourPassword.length() < 9) {
                    Toast.makeText(SignUpActivity.this, "Password Should be at least 8 digit.", Toast.LENGTH_LONG).show();
                    yourPassword.setError("Write 8-digit Password");
                    yourPassword.requestFocus();
                } else if (TextUtils.isEmpty(textReEnterPassword)) {
                    Toast.makeText(SignUpActivity.this, "Please re-Enter your password", Toast.LENGTH_LONG).show();
                    reEnterPassword.setError("Password is required");
                    reEnterPassword.requestFocus();
                } else if (!textYourPassword.equals(textReEnterPassword)) {
                    Toast.makeText(SignUpActivity.this, "Please use same password", Toast.LENGTH_LONG).show();
                    reEnterPassword.setError("Same Password is required");
                    reEnterPassword.requestFocus();

                    //Clear the entered Password
                    yourPassword.clearComposingText();
                    reEnterPassword.clearComposingText();
                } else {
                    registerUser(textFullName, textYourEmail, textYourPassword);
                }


            }


            //Register user using credentials given
            private void registerUser(String textFullName, String textYourEmail, String textYourPassword) {
                FirebaseAuth auth = FirebaseAuth.getInstance();

                //Create User Profile
                auth.createUserWithEmailAndPassword(textYourEmail, textYourPassword).addOnCompleteListener(SignUpActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();

                        //Enter User Data into the firebase Realtime Database
                        ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textFullName,textYourEmail);

                        //Extracting user reference from Database for Registered User
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");

                        reference.child(Objects.requireNonNull(firebaseUser).getUid()).setValue(writeUserDetails).addOnCompleteListener(task1 -> {

                            if(task1.isSuccessful()){

                                //Send Verification Email
                                firebaseUser.sendEmailVerification();

                                Toast.makeText(SignUpActivity.this, "User Signed Up Successfully. Please verify your email.", Toast.LENGTH_SHORT).show();


                                //Go back to Sign In page
                                Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);

                                //prevent user from returning back to registerActivity
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish(); //Can't go back when backBtn Clicked

                            }else {
                                Toast.makeText(SignUpActivity.this, "User registered failed. Try again!", Toast.LENGTH_SHORT).show();
                            }


                        });




                    } else {
                        try {
                            throw Objects.requireNonNull(task.getException());
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            yourEmail.setError("Your email is invalid or already in use.");
                            yourEmail.requestFocus();
                        } catch (FirebaseAuthUserCollisionException e) {
                            yourEmail.setError("User is already registered. Use another email.");
                        } catch (Exception e) {
                            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}