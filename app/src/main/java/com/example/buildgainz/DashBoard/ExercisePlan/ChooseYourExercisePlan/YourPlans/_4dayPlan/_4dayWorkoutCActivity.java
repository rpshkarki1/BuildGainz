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

public class _4dayWorkoutCActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_4day_workout_cactivity );
        Toolbar toolbar = findViewById ( R.id.toolbarWorkout4C );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Incline_Bench_Press_-_Medium_Grip/1.webp", "Barbell Incline Bench Press", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Dumbbell_Flyes/1.webp", "Dumbbell Flyes", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Seated_Cable_Rows/1.webp", "Seated Cable Rows", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/One-Arm_Dumbbell_Row/1.webp", "One-Arm Dumbbell Row", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Side_Lateral_Raise/1.webp", "Side Lateral Raises", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Alternate_Incline_Dumbbell_Curl/1.webp", "Alternate Incline Dumbbell Curl", "3 sets of 8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Triceps_Pushdown_-_Rope_Attachment/1.webp", "Cable One Arm Tricep Extension", "3 sets of 10-12 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout4C );
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