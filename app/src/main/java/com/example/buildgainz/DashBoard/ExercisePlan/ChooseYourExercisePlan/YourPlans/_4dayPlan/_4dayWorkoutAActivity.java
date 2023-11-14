package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._4dayPlan;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutItem;
import com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans.WorkoutsAdapter;
import com.example.buildgainz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _4dayWorkoutAActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_4day_workout_aactivity );

        Toolbar toolbar = findViewById ( R.id.toolbarWorkout4A );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Bench_Press_-_Medium_Grip/1.webp", "Barbell Bench Press", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Incline_Dumbbell_Press/1.webp", "Incline Dumbbell Press", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Bent_Over_Barbell_Row/1.webp", "Bent Over Barbell Row", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Wide-Grip_Lat_Pulldown/1.webp", "Wide-Grip Lat Pulldown", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Shoulder_Press/1.webp", "Barbell Shoulder Press", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Curl/1.webp", "Barbell Curl", "3 sets of 10-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/EZ-Bar_Skullcrusher/1.webp", "EZ-Bar Skullcrusher", "3 sets of 8-12 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout4A );
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