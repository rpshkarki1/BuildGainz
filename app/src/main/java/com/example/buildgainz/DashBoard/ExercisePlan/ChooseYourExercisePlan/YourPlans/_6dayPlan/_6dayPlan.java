package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._6dayPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;

import java.util.Objects;

public class _6dayPlan extends AppCompatActivity {
    RelativeLayout workout_A_CardView, workout_B_CardView, workout_C_CardView,workout_D_CardView,workout_E_CardView,workout_F_CardView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_6day_plan );
        Toolbar toolbar = findViewById ( R.id._6dayPlan );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        workout_A_CardView = findViewById ( R.id.workout_1_CardView );
        workout_B_CardView = findViewById ( R.id.workout_2_CardView );
        workout_C_CardView = findViewById ( R.id.workout_3_CardView );
        workout_D_CardView = findViewById ( R.id.workout_4_CardView );
        workout_E_CardView = findViewById ( R.id.workout_5_CardView );
        workout_F_CardView = findViewById ( R.id.workout_6_CardView );


        workout_A_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _6dayPlan.this , _6dayWorkoutAActivity.class ) ) );
        workout_B_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _6dayPlan.this , _6dayWorkoutBActivity.class ) ) );
        workout_C_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _6dayPlan.this , _6dayWorkoutCActivity.class ) ) );
        workout_D_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _6dayPlan.this , _6dayWorkoutDActivity.class ) ) );
        workout_E_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _6dayPlan.this , _6dayWorkoutEActivity.class ) ) );
        workout_F_CardView.setOnClickListener ( v -> startActivity ( new Intent ( _6dayPlan.this , _6dayWorkoutFActivity.class ) ) );



    } //Back Button
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == android.R.id.home) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}