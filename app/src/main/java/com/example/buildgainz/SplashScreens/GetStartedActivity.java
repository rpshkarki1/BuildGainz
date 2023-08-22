package com.example.buildgainz.SplashScreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildgainz.DashBoard.DashBoardActivity;
import com.example.buildgainz.LoginPage.LoginPageActivity;
import com.example.buildgainz.R;
import com.google.firebase.auth.FirebaseAuth;

public class GetStartedActivity extends AppCompatActivity {
    FirebaseAuth authProfile;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

        authProfile = FirebaseAuth.getInstance ( );
        if ( authProfile.getCurrentUser ( ) != null ) {
            // User is already logged in, go to Dashboard
            startActivity ( new Intent ( GetStartedActivity.this , DashBoardActivity.class ) );
            finish ( );
            return; // Exit the method to avoid the rest of the code execution
        }
        setContentView ( R.layout.activity_get_started );

        Button btnGetStart;

        btnGetStart = findViewById ( R.id.getStartedButton );

        btnGetStart.setOnClickListener ( v -> {
            Intent iGetStart;
            iGetStart = new Intent ( GetStartedActivity.this , LoginPageActivity.class );
            startActivity ( iGetStart );
            finish ( );
        } );


    }


}