package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.buildgainz.R;

import java.util.Objects;

public class YourExercisePlan extends AppCompatActivity {

    RelativeLayout _3day,_4day,_5day,_6day;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_your_plan );
        Toolbar toolbar = findViewById ( R.id.toolbarYourPlan);
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        _3day = findViewById ( R.id._3DayCardView );
        _4day=findViewById ( R.id._4DayCardView );
        _5day=findViewById ( R.id._5DayCardView );
        _6day=findViewById ( R.id._6DayCardView );

        _3day.setOnClickListener ( v -> startActivity (new Intent ( YourExercisePlan.this, _3dayPlan.class ) ));
        _4day.setOnClickListener ( v -> startActivity (new Intent (  YourExercisePlan.this, _4dayPlan.class ) ));
        _5day.setOnClickListener ( v -> startActivity ( new Intent ( YourExercisePlan.this, _5dayPlan.class ) ));
        _6day.setOnClickListener ( v -> startActivity ( new Intent(  YourExercisePlan.this, _6dayPlan.class )) );
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