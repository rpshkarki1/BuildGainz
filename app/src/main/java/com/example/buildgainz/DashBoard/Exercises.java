package com.example.buildgainz.DashBoard;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Exercises extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_exercises );

        Toolbar toolbar = findViewById ( R.id.toolbarExercise );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

    }

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url("https://health-and-fitness-api.p.rapidapi.com/%7BPATH%7D")
            .get()
            .build();

    Response response;

    {
        try {
            response = client.newCall(request).execute ();
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }


    //Back Button
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if ( item.getItemId ( ) == android.R.id.home ) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}