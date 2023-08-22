package com.example.buildgainz.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.buildgainz.R;

@SuppressLint ( "CustomSplashScreen" )
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_screen );
        Intent getStarted = new Intent ( SplashScreenActivity.this , GetStartedActivity.class );

        new Handler ( ).postDelayed ( ( ) -> {
            startActivity ( getStarted );
            finish ( );
        } , 2000 );
    }
}