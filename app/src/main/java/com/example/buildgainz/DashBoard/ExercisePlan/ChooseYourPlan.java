package com.example.buildgainz.DashBoard.ExercisePlan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.AllExerciseList.AllExerciseActivity;
import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.YourExercisePlan;
import com.example.buildgainz.R;

import java.util.Objects;

public class ChooseYourPlan extends AppCompatActivity {

    RelativeLayout allExercisePlan, yourPlan;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_choose_your_plan );
        Toolbar toolbar = findViewById ( R.id.toolbarChoose );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );
        allExercisePlan = findViewById ( R.id.allExerCardView );
        yourPlan = findViewById ( R.id.yourPlanCardView);

        allExercisePlan.setOnClickListener (v-> startActivity (new Intent ( ChooseYourPlan.this, AllExerciseActivity.class )));
        yourPlan.setOnClickListener ( v -> startActivity ( new Intent ( ChooseYourPlan.this, YourExercisePlan.class ) ) );
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