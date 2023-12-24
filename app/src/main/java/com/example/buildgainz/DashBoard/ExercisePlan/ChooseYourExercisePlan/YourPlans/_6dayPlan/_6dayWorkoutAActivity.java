package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._6dayPlan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutItem;
import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutsAdapter;
import com.example.buildgainz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _6dayWorkoutAActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_6day_workout_aactivity );

        Toolbar toolbar = findViewById ( R.id.toolbarWorkout6A );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Bench_Press_-_Medium_Grip/1.webp", "Barbell Bench Press", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Dumbbell_Shoulder_Press/1.webp", "Dumbbell Shoulder Press", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Dips_-_Triceps_Version/1.webp", "Dips - Triceps Version", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Dumbbell_Flyes/1.webp", "Dumbbell Flyes", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Seated_Side_Lateral_Raise/1.webp", "Seated Side Lateral Raise", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Pushups_Close_and_Wide_Hand_Positions/1.webp", "Pushups (Close and Wide Hand Positions)", "3 sets of 8-12 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout6A );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        WorkoutsAdapter workoutAdapter = new WorkoutsAdapter ( workoutItemList , this );
        recyclerView.setAdapter ( workoutAdapter );
    }


    @Override
    public void onItemClick ( int position ) {
        Toast.makeText ( this , "Item clicked at position: " + position , Toast.LENGTH_SHORT ).show ( );

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