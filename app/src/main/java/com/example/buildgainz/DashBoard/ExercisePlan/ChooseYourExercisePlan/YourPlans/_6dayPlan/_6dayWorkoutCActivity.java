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

public class _6dayWorkoutCActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_6day_workout_cactivity );
        Toolbar toolbar = findViewById ( R.id.toolbarWorkout6C );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Squat/1.webp", "Barbell Squat", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Hip_Thrust/1.webp", "Barbell Hip Thrust", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Romanian_Deadlift/1.webp", "Romanian Deadlift", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Split_Squat_with_Dumbbells/1.webp", "Split Squat with Dumbbells", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Seated_Calf_Raise/1.webp", "Seated Calf Raise", "3 sets of 8-12 reps"));

        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout6C );
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