package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;

import java.util.Objects;

public class _3dayPlan extends AppCompatActivity {

    RelativeLayout workout_A_CardView, workout_B_CardView, workout_C_CardView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_3day_plan );
        Toolbar toolbar = findViewById ( R.id._3dayPlan );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        workout_A_CardView = findViewById ( R.id.workout_1_CardView );
        workout_B_CardView = findViewById ( R.id.workout_3_CardView );
        workout_C_CardView = findViewById ( R.id.workout_5_CardView );


        workout_A_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _3dayPlan.this , _3dayWorkoutAActivity.class ) ) );
        workout_B_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _3dayPlan.this , _3dayWorkoutBActivity.class ) ) );
        workout_C_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _3dayPlan.this , _3dayWorkoutCActivity.class ) ) );

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