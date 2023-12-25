package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.buildgainz.R;

import java.util.Objects;

public class WorkoutViewActivity extends AppCompatActivity {

    private String workoutPath;
    private int currentImageIndex;
    private ImageView imageView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);

        Toolbar toolbar = findViewById(R.id.toolbarExercise);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView nameTextView = findViewById(R.id.name);
        TextView levelTextView = findViewById(R.id.level);
        TextView textViewInstruction = findViewById(R.id.textViewInstruction);
        imageView = findViewById(R.id.image_view);
        AssetManager assetManager = getAssets ( );

        Intent intent = getIntent();
        String workoutName = intent.getStringExtra("workoutName");
        String reps = intent.getStringExtra("reps");
        workoutPath = intent.getStringExtra("workoutImagePath");
        String instruction = intent.getStringExtra("instruction");
        nameTextView.setText(Objects.requireNonNull(workoutName).toUpperCase());
        levelTextView.setText(Objects.requireNonNull(reps).toUpperCase());
        textViewInstruction.setText(Objects.requireNonNull(instruction));


      //  Glide.get(this).getRegistry().prepend(String.class, InputStream.class, new AssetPathFetcher.Factory(assetManager));

        loadCurrentImage();

        // Set listeners for image switching
        ImageButton leftButton = findViewById(R.id.leftImageButton);
        ImageButton rightButton = findViewById(R.id.rightImageButton);

        leftButton.setOnClickListener(v -> switchImage(-1)); // Switch to the previous image
        rightButton.setOnClickListener(v -> switchImage(1));
    }

    private void switchImage(int direction) {
        currentImageIndex += direction;

        int totalImages = getTotalImages();

        if (currentImageIndex < 0) {
            currentImageIndex = totalImages - 1;
        } else if (currentImageIndex >= totalImages) {
            currentImageIndex = 0;
        }

        loadCurrentImage();
    }

    private int getTotalImages() {
        // You can implement a method to count the number of images in the folder
        // or hardcode the total number based on your requirements.
        return 2; // Change this value based on your actual number of images
    }

    private void loadCurrentImage() {
        // Load the new image using Glide and AssetManager
        String imagePath = workoutPath + currentImageIndex + ".webp";
        String imageUrl = "file:///android_asset/" + imagePath;
        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions())
                .into(imageView);
    }


    // Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
