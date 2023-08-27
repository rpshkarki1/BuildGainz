package com.example.buildgainz.DashBoard.ExercisePlan.ViewExercise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        ImageButton left = findViewById ( R.id.leftImageButton );
        ImageButton right = findViewById ( R.id.rightImageButton );
        imageSwitcher = findViewById ( R.id.image_switcher );

        Toolbar toolbar = findViewById ( R.id.toolbarExercise );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        Intent intent = getIntent ( );
        if (intent != null && intent.hasExtra ( EXTRA_SIMPLE_EXERCISE )) {
            ExerciseCnstrForViewActivity exerciseCnstrForViewActivity = intent.getParcelableExtra ( EXTRA_SIMPLE_EXERCISE );

            TextView nameTextView = findViewById ( R.id.name );
            TextView forceTextView = findViewById ( R.id.force );
            TextView levelTextView = findViewById ( R.id.level );
            TextView primaryMuscleTextView = findViewById ( R.id.primeMuscle );
            TextView textViewInstruction = findViewById ( R.id.textViewInstruction );


            nameTextView.setText ( Objects.requireNonNull ( exerciseCnstrForViewActivity ).getName ( ).toUpperCase ( ) );
            forceTextView.setText ("Force: " + exerciseCnstrForViewActivity.getForce ( ).toUpperCase ( ) );
            levelTextView.setText ("Level: " + exerciseCnstrForViewActivity.getLevel ( ).toUpperCase ( ) );
            primaryMuscleTextView.setText ( "Primary Muscle: " + exerciseCnstrForViewActivity.getPrimaryMuscles ( ) );


            StringBuilder instructions = new StringBuilder ( );
            for (String instruction : exerciseCnstrForViewActivity.getInstructions ( )) {
                instructions.append ( instruction ).append ( "\n" );
            }
            textViewInstruction.setText ( instructions.toString ( ) );

            left.setOnClickListener ( view -> showPreviousImage ( ) );
            right.setOnClickListener ( view -> showNextImage ( ) );

            imageSwitcher.setFactory ( ( ) -> {
                ImageView imageView = new ImageView ( getApplicationContext ( ) );
                imageView.setLayoutParams ( new FrameLayout.LayoutParams (
                        FrameLayout.LayoutParams.MATCH_PARENT ,
                        FrameLayout.LayoutParams.MATCH_PARENT ) );
                return imageView;
            } );


            // Load and display the first exercise image
            loadExerciseImage ( exerciseCnstrForViewActivity.getImageSubdirectory ( ) , exerciseCnstrForViewActivity.getImageFilename ( ) );
        }
    }

    private void loadExerciseImage ( String subdirectory , String filename ) {
        try {
            AssetManager assetManager = getAssets ( );
            String[] assetsList = assetManager.list ( "exercises_img/" + subdirectory );

            if (assetsList != null && assetsList.length >= 2) {
                String imagePath1 = "exercises_img/" + subdirectory + "/" + assetsList[0];
                String imagePath2 = "exercises_img/" + subdirectory + "/" + assetsList[1];

                Log.d ( "ExerciseViewActivity" , "Loading image 1: " + imagePath1 );
                InputStream inputStream1 = assetManager.open ( imagePath1 );
                Drawable drawable1 = Drawable.createFromStream ( inputStream1 , null );

                Log.d ( "ExerciseViewActivity" , "Loading image 2: " + imagePath2 );
                InputStream inputStream2 = assetManager.open ( imagePath2 );
                Drawable drawable2 = Drawable.createFromStream ( inputStream2 , null );

                ImageView imageView = (ImageView) imageSwitcher.getNextView ( );
                imageView.setImageDrawable ( drawable1 );

                imageSwitcher.showNext ( );

                ImageView nextImageView = (ImageView) imageSwitcher.getNextView ( );
                nextImageView.setImageDrawable ( drawable2 );

                inputStream1.close ( );
                inputStream2.close ( );
            } else {
                Log.e ( "ExerciseViewActivity" , "Insufficient assets found in directory." );
            }
        } catch ( IOException e ) {
            e.printStackTrace ( );
            Log.e ( "ExerciseViewActivity" , "Error loading image: " + e.getMessage ( ) );
        }
    }


    private void showPreviousImage ( ) {

        imageSwitcher.showPrevious ( );
    }

    private void showNextImage ( ) {

        imageSwitcher.showNext ( );
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
