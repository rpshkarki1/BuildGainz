package com.example.buildgainz.DashBoard.Calculator.BMI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;

import java.util.Objects;

public class BMIViewActivity extends AppCompatActivity {

    TextView bmiDisplay, ageDisplay, weightDisplay, heightDisplay, bmiCategory, gender;
    Button goToMain;
    Intent intent;

    ImageView imageView;
    String bmi;
    String category;
    float intBmi;

    String height;
    String weight;

    float intHeight, intWeight;

    RelativeLayout background;

    @SuppressLint ( {"ResourceAsColor" , "SetTextI18n"} )
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_bmiview );
        ColorDrawable colorDrawable = new ColorDrawable ( Color.parseColor ( "#1E1D1D" ) );

        Toolbar toolbar = findViewById ( R.id.toolbarBMIView );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        intent = getIntent ( );
        bmiDisplay = findViewById ( R.id.bmiDisplay );
        bmiCategory = findViewById ( R.id.bmiCategoryDisplay );
        goToMain = findViewById ( R.id.gotoMain );

        imageView = findViewById ( R.id.imageview );

        gender = findViewById ( R.id.genderDisplay );
        background = findViewById ( R.id.contentLayout );


        height = intent.getStringExtra ( "height" );
        weight = intent.getStringExtra ( "weight" );


        intHeight = Float.parseFloat ( height );
        intWeight = Float.parseFloat ( weight );

        intHeight = intHeight / 100;
        intBmi = intWeight / (intHeight * intHeight);


        bmi = Float.toString ( intBmi );
        System.out.println ( bmi );

        if (intBmi < 16) {
            bmiCategory.setText ( "Severe Thinness" );
            //   background.setBackgroundColor(Color.GRAY);
            background.setBackgroundColor ( Color.RED );
            imageView.setImageResource ( R.drawable.crosss );
            //  imageView.setBackground(colorDrawable2);

        } else if (intBmi < 16.9 && intBmi > 16) {
            bmiCategory.setText ( "Moderate Thinness" );
            background.setBackgroundColor ( R.color.half_warn );
            imageView.setImageResource ( R.drawable.warning );

        } else if (intBmi < 18.4 && intBmi > 17) {
            bmiCategory.setText ( "Mild Thinness" );
            background.setBackgroundColor ( R.color.half_warn );
            imageView.setImageResource ( R.drawable.warning );
        } else if (intBmi < 24.9 && intBmi > 18.5) {
            bmiCategory.setText ( "Normal" );
            imageView.setImageResource ( R.drawable.ok );
        } else if (intBmi < 29.9 && intBmi > 25) {
            bmiCategory.setText ( "Overweight" );
            background.setBackgroundColor ( R.color.half_warn );
            imageView.setImageResource ( R.drawable.warning );
        } else if (intBmi < 34.9 && intBmi > 30) {
            bmiCategory.setText ( "Obese Class I" );
            background.setBackgroundColor ( R.color.half_warn );
            imageView.setImageResource ( R.drawable.warning );
        } else {
            bmiCategory.setText ( "Obese Class II" );
            background.setBackgroundColor ( R.color.warn );
            imageView.setImageResource ( R.drawable.crosss );
        }

        gender.setText ( intent.getStringExtra ( "gender" ) );
        bmiDisplay.setText ( bmi );


        goToMain.setOnClickListener ( v -> {
            Intent intent1 = new Intent ( BMIViewActivity.this , BMICalculatorActivity.class );
            startActivity ( intent1 );
        } );


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