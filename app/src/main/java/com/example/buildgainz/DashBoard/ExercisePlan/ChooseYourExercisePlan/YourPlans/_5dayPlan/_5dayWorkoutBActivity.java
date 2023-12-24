package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._5dayPlan;

import android.os.Bundle;
import android.view.MenuItem;

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

public class _5dayWorkoutBActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_5day_workout_bactivity );

        Toolbar toolbar = findViewById ( R.id.toolbarWorkout5B );
        setSupportActionBar ( toolbar );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Deadlift/1.webp", "Barbell Deadlift", "4 sets of 3-6 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Pullups/1.webp", "Pullupss", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Bent_Over_Barbell_Row/1.webp", "Bent Over Barbell Row", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Wide-Grip_Lat_Pulldown/1.webp", "Wide-Grip Lat Pulldown", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Leverage_High_Row/1.webp", "Leverage High Row", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Decline_Push-Up/1.webp", "Decline Push-Up", "3 sets of 8-12 reps"));





        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout5B );
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