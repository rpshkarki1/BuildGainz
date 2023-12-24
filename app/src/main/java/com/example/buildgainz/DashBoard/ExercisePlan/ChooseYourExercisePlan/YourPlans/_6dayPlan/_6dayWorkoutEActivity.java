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

public class _6dayWorkoutEActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_6day_workout_eactivity );
        Toolbar toolbar = findViewById ( R.id.toolbarWorkout6E );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );
        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Pullups/1.webp", "Pullups", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Bent_Over_Barbell_Row/1.webp", "Bent Over Barbell Row", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/T-Bar_Row_with_Handle/1.webp", "T-Bar Row with Handle", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Cable_Rear_Delt_Fly/1.webp", "Cable Rear Delt Fly", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Dumbbell_Shrug/1.webp", "Dumbbell Shrug", "3 sets of 10-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Alternate_Hammer_Curl/1.webp", "Alternate Hammer Curl", "3 sets of 8-12 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout6E );
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