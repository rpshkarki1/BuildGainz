package com.example.buildgainz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buildgainz.SignUpPage.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;


public class LoginPageActivity extends AppCompatActivity {

    private static final String TAG = "LoginPageActivity";
    private EditText emailEditText, passEditText;
    private TextView signUp, forgotPass;
    private FirebaseAuth authProfile;
    private Button buttonSignIn;
    private ImageView imageViewShowHidePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //When Clicked Sign Up now Button
        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPageActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        //When Clicked Forgot Password Button
        forgotPass = findViewById(R.id.forgotPass);
        forgotPass.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPageActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        emailEditText = findViewById(R.id.editTextYourEmail);
        passEditText = findViewById(R.id.editTextYourPassword);
        imageViewShowHidePwd = findViewById(R.id.imgViewShowHide);
        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_pwd);

        //Show hide password using Eye icon using imgView
        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passEditText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {

                    //if pwd is visible then hides it
                    passEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //Then Change Icon
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_show_pwd);
                } else{
                    passEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        imageViewShowHidePwd.setImageResource(R.drawable.ic_hide_pwd);

                }
            }
        });


        authProfile = FirebaseAuth.getInstance();

        buttonSignIn = findViewById(R.id.signInBtn);
        //When Clicked Sign In Button
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textEmail = emailEditText.getText().toString();
                String textPassword = passEditText.getText().toString();

                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(LoginPageActivity.this, "Please Enter Your Email.", Toast.LENGTH_SHORT).show();
                    emailEditText.setError("Email is required!");
                    emailEditText.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(LoginPageActivity.this, "Please re-Enter your Email.", Toast.LENGTH_SHORT).show();
                    emailEditText.setError("Valid Email is required!");
                    emailEditText.requestFocus();
                } else if (TextUtils.isEmpty(textPassword)) {
                    Toast.makeText(LoginPageActivity.this, "Please enter your Password.", Toast.LENGTH_SHORT).show();
                    passEditText.setError("Password is required!");
                    passEditText.requestFocus();
                } else {
                    loginUser(textEmail, textPassword);
                }

            }

            private void loginUser(String email, String password) {
                authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginPageActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginPageActivity.this, "You are logged in now.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginPageActivity.this, DashBoardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            try {
                                throw Objects.requireNonNull(task.getException());
                            } catch (FirebaseAuthInvalidUserException e) {
                                emailEditText.setError("User does not exists or is no longer valid. Please sign up again.");
                                emailEditText.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                emailEditText.setError("Invalid credentials. Please check again and re-Enter.");
                                emailEditText.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                                Toast.makeText(LoginPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });
            }
        });
    }
}