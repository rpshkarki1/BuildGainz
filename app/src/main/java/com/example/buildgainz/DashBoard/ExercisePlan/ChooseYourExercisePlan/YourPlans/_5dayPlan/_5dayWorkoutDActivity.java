package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._5dayPlan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutItem;
import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutsAdapter;
import com.example.buildgainz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _5dayWorkoutDActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_5day_workout_dactivity );

        Toolbar toolbar = findViewById ( R.id.toolbarWorkout5D );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Squat/1.webp", "Barbell Squat", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Romanian_Deadlift/1.webp", "Romanian Deadlift", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Split_Squat_with_Dumbbells/1.webp", "Split Squat with Dumbbells", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Hip_Thrust/1.webp", "Barbell Hip Thrust", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Seated_Leg_Curl/1.webp", "Seated Leg Curl", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Seated_Calf_Raise/1.webp", "Seated Calf Raise", "3 sets of 8-12 reps"));



        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout5D );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        WorkoutsAdapter workoutAdapter = new WorkoutsAdapter ( workoutItemList , this );
        recyclerView.setAdapter ( workoutAdapter );
    }   //Back Button
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

    @Override
    public void onItemClick ( int position ) {

    }
}