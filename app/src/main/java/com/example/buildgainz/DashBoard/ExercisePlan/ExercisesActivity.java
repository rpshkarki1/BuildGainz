package com.example.buildgainz.DashBoard.ExercisePlan;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ExercisesActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_exercises );

        Toolbar toolbar = findViewById ( R.id.toolbarExercise );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        RecyclerView recyclerView = findViewById ( R.id.recyclerExercise );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );

        List < Exercise > exercises = loadExercisesFromJson ( );

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter ( this , exercises );
        recyclerView.setAdapter ( exerciseAdapter );

    }

    private List<Exercise> loadExercisesFromJson() {
        List<Exercise> exercises = new ArrayList<>();

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.exercises_list);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Exercise exercise = new Exercise();
                exercise.setName(jsonObject.getString("name"));
                exercise.setForce(jsonObject.getString("force"));
                exercise.setLevel(jsonObject.getString("level"));
                exercise.setMechanic(jsonObject.getString("mechanic"));
                exercise.setEquipment(jsonObject.getString("equipment"));

                // Extract and set primary muscles
                JSONArray primaryMusclesArray = jsonObject.getJSONArray("primaryMuscles");
                List<String> primaryMuscles = new ArrayList<>();
                for (int j = 0; j < primaryMusclesArray.length(); j++) {
                    primaryMuscles.add(primaryMusclesArray.getString(j));
                }
                exercise.setPrimaryMuscles(primaryMuscles);

                // Extract and set secondary muscles
                JSONArray secondaryMusclesArray = jsonObject.getJSONArray("secondaryMuscles");
                List<String> secondaryMuscles = new ArrayList<>();
                for (int j = 0; j < secondaryMusclesArray.length(); j++) {
                    secondaryMuscles.add(secondaryMusclesArray.getString(j));
                }
                exercise.setSecondaryMuscles(secondaryMuscles);

                // Extract and set instructions
                JSONArray instructionsArray = jsonObject.getJSONArray("instructions");
                List<String> instructions = new ArrayList<>();
                for (int j = 0; j < instructionsArray.length(); j++) {
                    instructions.add(instructionsArray.getString(j));
                }
                exercise.setInstructions(instructions);

                exercise.setCategory(jsonObject.getString("category"));

                // Extract and set image filename and subdirectory
                JSONArray imagesArray = jsonObject.getJSONArray("images");
                if (imagesArray.length() > 0) {
                    String imagePath = imagesArray.getString(0); // Assuming the first image is used
                    String[] imagePathComponents = imagePath.split("/");
                    if (imagePathComponents.length >= 2) {
                        exercise.setImageFilename(imagePathComponents[imagePathComponents.length - 1].replace(".jpg", ""));
                        exercise.setImageSubdirectory(imagePathComponents[imagePathComponents.length - 2]);
                    }
                }

                exercises.add(exercise);
            }
        } catch ( IOException | JSONException e) {
            e.printStackTrace();
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
}