package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._4dayPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutItem;
import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutViewActivity;
import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutsAdapter;
import com.example.buildgainz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _4dayWorkoutBActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {
    List < WorkoutItem > workoutItemList;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_4day_workout_bactivity );

        Toolbar toolbar = findViewById ( R.id.toolbarWorkout4B );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


         workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Squat/","1.webp", "Barbell Squat", "4 sets of 8-15 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Clean_Deadlift/","0.webp", "Clean Deadlift", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Leg_Press/","1.webp", "Leg Press", "3 sets of 10-15 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Lying_Leg_Curls/","1.webp", "Lying Leg Curl", "3 sets of 10-15 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Standing_Dumbbell_Calf_Raise/","1.webp", "Standing Dumbbell Calf Raise", "3 sets of 15 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout4B );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        WorkoutsAdapter workoutAdapter = new WorkoutsAdapter ( workoutItemList , this );
        recyclerView.setAdapter ( workoutAdapter );


    }

    @Override
    public void onItemClick ( int position ) {
        WorkoutItem selectedWorkout = workoutItemList.get(position);

        String instruction = "Workout Routine\n" +
                "\n" +
                "1. Warm-up:\n" +
                "\n" +
                "Do 1 or 2 set of low weight for warming up for blood flow in body parts.\n" +"\n" +
                "2. Exercise Sets (1-4):\n" +
                "\n" +
                "Perform 8-12 reps with proper form.\n" +
                "Rest 60-90 seconds between sets.\n" +
                "Repeat for required sets as said in upper.";
        Intent intent = new Intent ( _4dayWorkoutBActivity.this , WorkoutViewActivity.class );
        intent.putExtra("workoutName", selectedWorkout.getName());
        intent.putExtra("workoutImagePath", selectedWorkout.getImagePath());
        intent.putExtra("reps", selectedWorkout.getRepsSets ());
        intent.putExtra("instruction", instruction);


        startActivity ( intent );
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

    @Override
    public void onPointerCaptureChanged ( boolean hasCapture ) {
        super.onPointerCaptureChanged ( hasCapture );
    }

}