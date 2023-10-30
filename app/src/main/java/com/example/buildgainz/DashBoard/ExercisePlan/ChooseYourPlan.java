package com.example.buildgainz.DashBoard.ExercisePlan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.buildgainz.DashBoard.ExercisePlan.AllExerciseList.AllExerciseActivity;
import com.example.buildgainz.R;

public class ChooseYourPlan extends AppCompatActivity {

    RelativeLayout allExercisePlan, yourPlan;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_choose_your_plan );

        allExercisePlan = findViewById ( R.id.allExerCardView );
        yourPlan = findViewById ( R.id.yourPlanCardView);



        allExercisePlan.setOnClickListener (v-> startActivity (new Intent ( ChooseYourPlan.this, AllExerciseActivity.class )));



    }
}