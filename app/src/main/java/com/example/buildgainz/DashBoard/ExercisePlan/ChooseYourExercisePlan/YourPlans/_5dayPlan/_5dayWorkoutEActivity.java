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
import java.util.Objects;

public class _5dayWorkoutEActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_5day_workout_eactivity );
        Toolbar toolbar = findViewById ( R.id.toolbarWorkout5E );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Dumbbell_Shoulder_Press/1.webp", "Dumbbell Shoulder Press", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Arnold_Dumbbell_Press/1.webp", "Arnold Dumbbell Press", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Side_Lateral_Raise/1.webp", "Side Lateral Raise", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Front_Cable_Raise/1.webp", "Front Cable Raise", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Cable_Rear_Delt_Fly/1.webp", "Cable Rear Delt Fly", "4 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Face_Pull/1.webp", "Face Pull", "3 sets of 8-12 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout5E );
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