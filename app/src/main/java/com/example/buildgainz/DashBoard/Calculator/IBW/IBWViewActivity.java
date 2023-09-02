package com.example.buildgainz.DashBoard.Calculator.IBW;

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

public class IBWViewActivity extends AppCompatActivity {
    TextView iWDisplay, ageDisplay, heightDisplay;
    Button goToMain;
    Intent intent;

    String iW;
    float intIW;

    String height;
    String age, gender = "Male";

    float intHeight, intAge;

    @SuppressLint ( "SetTextI18n" )
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ibwview );
        Toolbar toolbar = findViewById ( R.id.toolbarBMRView );

        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        intent = getIntent ( );
        iWDisplay = findViewById ( R.id.ibwView );
        goToMain = findViewById ( R.id.gotoMain );


        height = intent.getStringExtra ( "height" );
        age = intent.getStringExtra ( "age" );
        gender = intent.getStringExtra ( "gender" );

        intHeight = Float.parseFloat ( height );
        intAge = Float.parseFloat ( age );

        if (gender.equals ( "Male" )) {
            intIW = (float) (52 + (1.9 * (intHeight/2.54 - 60 )));

        } else {
            intIW = (float) (49 + (1.7 * (intHeight/2.54 - 60)));
        }


        iW = Float.toString ( intIW );

        iWDisplay.setText ( iW + " kgs" );


        goToMain.setOnClickListener ( v -> {
            Intent intent1 = new Intent ( IBWViewActivity.this , IBWActivity.class );
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