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

public class _5dayWorkoutCActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_5day_workout_cactivity );

        Toolbar toolbar = findViewById ( R.id.toolbarWorkout5C );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Curl/1.webp", "Barbell Curl", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Alternate_Hammer_Curl/1.webp", "Alternate Hammer Curl", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Reverse_Cable_Curl/1.webp", "Reverse Cable Curl", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Triceps_Pushdown/1.webp", "Triceps Pushdown", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Tricep_Dumbbell_Kickback/1.webp", "Tricep Dumbbell Kickback", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Decline_Close-Grip_Bench_To_Skull_Crusher/1.webp", "Decline Close-Grip Bench To Skull Crusher", "3 sets of 8-12 reps"));

        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout5C );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        WorkoutsAdapter workoutAdapter = new WorkoutsAdapter ( workoutItemList , this );
        recyclerView.setAdapter ( workoutAdapter );
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

    @Override
    public void onItemClick ( int position ) {

    }
}