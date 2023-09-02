package com.example.buildgainz.DashBoard.Calculator.BMR;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;

import java.util.Objects;

public class BMRViewActivity extends AppCompatActivity {

    TextView bmrDisplay, ageDisplay, weightDisplay, heightDisplay;
    Button goToMain;
    Intent intent;

    String bmr;
    float intBmr;

    String height;
    String weight, age, gender = "Male";

    float intHeight, intWeight, intAge;

    @SuppressLint ( "SetTextI18n" )
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_bmrview );
        Toolbar toolbar = findViewById ( R.id.toolbarBMRView );

        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        intent = getIntent ( );
        bmrDisplay = findViewById ( R.id.calorieDisplay );
        goToMain = findViewById ( R.id.gotoMain );


        height = intent.getStringExtra ( "height" );
        weight = intent.getStringExtra ( "weight" );
        age = intent.getStringExtra ( "age" );
        gender = intent.getStringExtra ( "gender" );

        intHeight = Float.parseFloat ( height );
        intWeight = Float.parseFloat ( weight );
        intAge = Float.parseFloat ( age );

        if (gender.equals ( "Male" )) {
            intBmr = (float) ((10 * intWeight) + (6.25 * intHeight) - (5 * intAge) + 5);

        } else {
            intBmr = (float) ((10 * intWeight) + (6.25 * intHeight) - (5 * intAge) - 161);
        }


        bmr = Float.toString ( intBmr );

        bmrDisplay.setText ( bmr + " calorie/day" );


        goToMain.setOnClickListener ( v -> {
            Intent intent1 = new Intent ( BMRViewActivity.this , BMRCalculatorActivity.class );
            startActivity ( intent1 );
        } );
    }

    //Back Button
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == android.R.id.home) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}