package com.example.buildgainz.DashBoard.ExercisePlan.ViewExercise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildgainz.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ExerciseViewActivity extends AppCompatActivity {

    public static final String EXTRA_SIMPLE_EXERCISE = "extra_simple_exercise";

    private ImageSwitcher imageSwitcher;

    @SuppressLint ( "SetTextI18n" )
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_exercise_view );

        Intent intent = getIntent ( );
        if (intent != null && intent.hasExtra ( EXTRA_SIMPLE_EXERCISE )) {
            ExerciseCnstrForViewActivity exerciseCnstrForViewActivity = intent.getParcelableExtra ( EXTRA_SIMPLE_EXERCISE );

            TextView nameTextView = findViewById ( R.id.name );
            TextView forceTextView = findViewById ( R.id.force );
            TextView levelTextView = findViewById ( R.id.level );
            TextView primaryMuscleTextView = findViewById ( R.id.primeMuscle );
            TextView textViewInstruction = findViewById ( R.id.textViewInstruction );


            nameTextView.setText ( Objects.requireNonNull ( exerciseCnstrForViewActivity ).getName ( ) );
            forceTextView.setText ( exerciseCnstrForViewActivity.getForce ( ) );
            levelTextView.setText ( exerciseCnstrForViewActivity.getLevel ( ) );
            primaryMuscleTextView.setText ( "Primary Muscle: " + exerciseCnstrForViewActivity.getPrimaryMuscles ( ) );


            StringBuilder instructions = new StringBuilder ( );
            for (String instruction : exerciseCnstrForViewActivity.getInstructions ( )) {
                instructions.append ( instruction ).append ( "\n" );
            }
            textViewInstruction.setText ( instructions.toString ( ) );


            imageSwitcher = findViewById ( R.id.viewPager );
            imageSwitcher.setFactory ( ( ) -> {
                ImageView imageView = new ImageView ( getApplicationContext ( ) );
                imageView.setScaleType ( ImageView.ScaleType.FIT_CENTER );
                return imageView;
            } );

            // Load and display the first exercise image
            loadExerciseImage ( exerciseCnstrForViewActivity.getImageSubdirectory ( ) , exerciseCnstrForViewActivity.getImageFilename ( ) );
        }
    }

    private void loadExerciseImage(String subdirectory, String filename) {
        try {
            AssetManager assetManager = getAssets();
            String imagePath = "exercises_img/" + subdirectory + "/" + filename + "1.jpg";
            Log.d("ExerciseViewActivity", "Loading image: " + imagePath); // Add this line
            InputStream inputStream = assetManager.open(imagePath);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            ImageView imageView = (ImageView) imageSwitcher.getNextView();
            imageView.setImageDrawable(drawable);
            imageSwitcher.showNext();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ExerciseViewActivity", "Error loading image: " + e.getMessage());
        }
    }


}
