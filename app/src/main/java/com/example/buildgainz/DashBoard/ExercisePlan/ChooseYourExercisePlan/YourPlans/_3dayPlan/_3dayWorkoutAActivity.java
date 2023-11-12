package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans._3dayPlan;

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

public class _3dayWorkoutAActivity extends AppCompatActivity implements WorkoutsAdapter.OnItemClickListener {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_3day_workout_aactivity );
        Toolbar toolbar = findViewById ( R.id.toolbarWorkout);
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        List < WorkoutItem > workoutItemList = new ArrayList <> ( );

        workoutItemList.add(new WorkoutItem("exercises_img/Barbell_Bench_Press_-_Medium_Grip/1.webp", "Barbell Bench Press", "4 sets of  8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Incline_Dumbbell_Press/1.webp", "Incline Dumbbell Press", "3 sets of  8-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Dumbbell_Flyes/1.webp", "Dumbbell Fly", "3 sets of  10-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Triceps_Pushdown_-_Rope_Attachment/1.webp", "Cable One Arm Tricep Extension", "3 sets of  10-12 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Cable_One_Arm_Tricep_Extension/1.webp", "Tricep Extension", "2 sets of 10 reps"));
        workoutItemList.add(new WorkoutItem("exercises_img/Standing_Dumbbell_Calf_Raise/1.webp", "Standing Dumbbell Calf Raise", "3 sets of 15 reps"));


        RecyclerView recyclerView = findViewById ( R.id.recyclerWorkout );
        recyclerView.setLayoutManager(new LinearLayoutManager (this));
        WorkoutsAdapter workoutAdapter = new WorkoutsAdapter ( workoutItemList , this );
        recyclerView.setAdapter( workoutAdapter );


    }

    @Override
    public void onItemClick ( int position ) {
        Toast.makeText(this, "Item clicked at position: " + position, Toast.LENGTH_SHORT).show();

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
}