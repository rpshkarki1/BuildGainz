package com.example.buildgainz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginPageActivity extends AppCompatActivity {

    Button buttonSignIn;
    TextView signUp, forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //When Clicked Sign In Button

        Button buttonSignIn = findViewById(R.id.signInBtn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, DashBoard.class);
                startActivity(intent);
            }
        });

        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPass = findViewById(R.id.forgot_pass);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });


    }
}