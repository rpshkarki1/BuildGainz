package com.example.buildgainz.DashBoard.ExercisePlan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildgainz.DashBoard.ExercisePlan.ViewExercise.ExerciseCnstrForViewActivity;
import com.example.buildgainz.DashBoard.ExercisePlan.ViewExercise.ExerciseViewActivity;
import com.example.buildgainz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExercisesActivity extends AppCompatActivity implements RecyclerViewInterface {
    private List < Exercise > exercises;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_exercises );

        Toolbar toolbar = findViewById ( R.id.toolbarExercise );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        RecyclerView recyclerView = findViewById ( R.id.recyclerExercise );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );

        exercises = loadExercisesFromJson ( );

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter ( this , exercises , this );
        recyclerView.setAdapter ( exerciseAdapter );

    }

    private List < Exercise > loadExercisesFromJson ( ) {
        List < Exercise > exercises = new ArrayList <> ( );

        try {
            InputStream inputStream = getResources ( ).openRawResource ( R.raw.exercises_list );
            byte[] buffer = new byte[inputStream.available ( )];
            inputStream.read ( buffer );
            inputStream.close ( );

            String json = new String ( buffer , StandardCharsets.UTF_8 );
            JSONArray jsonArray = new JSONArray ( json );

            for (int i = 0; i < jsonArray.length ( ); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject ( i );

                Exercise exercise = new Exercise ( );
                exercise.setName ( jsonObject.getString ( "name" ) );
                exercise.setForce ( jsonObject.getString ( "force" ) );
                exercise.setLevel ( jsonObject.getString ( "level" ) );
                exercise.setEquipment ( jsonObject.getString ( "equipment" ) );

                // Extract and set primary muscles
                JSONArray primaryMusclesArray = jsonObject.getJSONArray ( "primaryMuscles" );
                List < String > primaryMuscles = new ArrayList <> ( );
                for (int j = 0; j < primaryMusclesArray.length ( ); j++) {
                    primaryMuscles.add ( primaryMusclesArray.getString ( j ) );
                }
                exercise.setPrimaryMuscles ( primaryMuscles );


                // Extract and set instructions
                JSONArray instructionsArray = jsonObject.getJSONArray ( "instructions" );
                List < String > instructions = new ArrayList <> ( );
                for (int j = 0; j < instructionsArray.length ( ); j++) {
                    instructions.add ( instructionsArray.getString ( j ) );
                }
                exercise.setInstructions ( instructions );

                exercise.setCategory ( jsonObject.getString ( "category" ) );

                // Extract and set image filename and subdirectory
                JSONArray imagesArray = jsonObject.getJSONArray ( "images" );
                if (imagesArray.length ( ) > 0) {
                    String imagePath = imagesArray.getString ( 0 ); // Assuming the first image is used
                    String[] imagePathComponents = imagePath.split ( "/" );
                    if (imagePathComponents.length >= 2) {
                        exercise.setImageFilename ( imagePathComponents[imagePathComponents.length - 1].replace ( ".jpg" , "" ) );
                        exercise.setImageSubdirectory ( imagePathComponents[imagePathComponents.length - 2] );
                    }
                }

                exercises.add ( exercise );
            }
        } catch ( IOException | JSONException e ) {
            e.printStackTrace ( );
        }

        return exercises;
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
    public void onItemClick ( int position ) {
        if (exercises != null && position >= 0 && position < exercises.size ( )) {
            Exercise clickedExercise = exercises.get ( position );

            ExerciseCnstrForViewActivity exerciseCnstrForViewActivity = new ExerciseCnstrForViewActivity ( );
            exerciseCnstrForViewActivity.setName ( clickedExercise.getName ( ) );
            exerciseCnstrForViewActivity.setForce ( clickedExercise.getForce ( ) );
            exerciseCnstrForViewActivity.setLevel ( clickedExercise.getLevel ( ) );
            exerciseCnstrForViewActivity.setPrimaryMuscles ( clickedExercise.getPrimaryMuscles ( ) );
            exerciseCnstrForViewActivity.setInstructions ( clickedExercise.getInstructions ( ) );
            exerciseCnstrForViewActivity.setImageFilename ( clickedExercise.getImageFilename ( ) );
            exerciseCnstrForViewActivity.setImageSubdirectory ( clickedExercise.getImageSubdirectory ( ) );

            Intent intent = new Intent ( ExercisesActivity.this , ExerciseViewActivity.class );
            intent.putExtra ( ExerciseViewActivity.EXTRA_SIMPLE_EXERCISE , exerciseCnstrForViewActivity );
            startActivity ( intent );
        } else {
            assert exercises != null;
            Log.d("ExercisesActivity", "Exercises size: " + exercises.size());

            Toast.makeText(ExercisesActivity.this, "Error loading exercises.", Toast.LENGTH_SHORT).show();
        }
    }
}