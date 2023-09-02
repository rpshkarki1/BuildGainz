package com.example.buildgainz.DashBoard.Calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.DashBoard.Calculator.BMI.BMICalculatorActivity;
import com.example.buildgainz.DashBoard.Calculator.BMR.BMRCalculatorActivity;
import com.example.buildgainz.DashBoard.Calculator.IBW.IBWActivity;
import com.example.buildgainz.R;

import java.util.Objects;

public class CalculatorActivity extends AppCompatActivity {
    RelativeLayout bmiCardView, bmrCardView, ibwCardView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_calculator );


        Toolbar toolbar = findViewById ( R.id.toolbarCalc );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        bmiCardView = findViewById ( R.id.bmiCardView );
        bmrCardView = findViewById ( R.id.bmrCardView );
        ibwCardView = findViewById ( R.id.ibwCardView );

        bmiCardView.setOnClickListener ( v -> startActivity ( new Intent ( CalculatorActivity.this , BMICalculatorActivity.class ) ) );

        bmrCardView.setOnClickListener ( v -> startActivity ( new Intent ( CalculatorActivity.this , BMRCalculatorActivity.class ) ) );

        ibwCardView.setOnClickListener ( v -> startActivity ( new Intent ( CalculatorActivity.this , IBWActivity.class ) ) );


    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == android.R.id.home) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}