package com.example.buildgainz.DashBoard.Tracking;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.buildgainz.R;

import java.text.DecimalFormat;
import java.util.Objects;

public class StepTrackingActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor stepSensor;
    private int totalSteps = 0;
    private int previewTotalSteps = 0;
    private ProgressBar progressBar;
    private TextView steps, totalDistance;
    int goalStep = 8500, currentSteps;
    double averageStrideLengthMeters = 0.762;
    private static final int REQUEST_ACTIVITY_RECOGNITION_PERMISSION = 1001;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracking);

        // Check if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION},
                    REQUEST_ACTIVITY_RECOGNITION_PERMISSION);
        } else {
            // Permission is already granted
            initializeStepTracking();
        }

        progressBar = findViewById(R.id.progressBar);
        steps = findViewById(R.id.steps);
        totalDistance = findViewById(R.id.totalDistance);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        onResume();

        Toolbar toolbar = findViewById(R.id.toolbarTracking);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        resetSteps();
    }

    private void initializeStepTracking() {
        if (sensorManager == null) {
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        }
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepSensor == null) {
            Toast.makeText(this, "This device has no step counter sensor.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor == null) {
            Toast.makeText(this, "This device has no sensor.", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = (int) event.values[0];
            currentSteps = totalSteps - previewTotalSteps;
            steps.setTextColor(getColor(currentSteps > 3000 ? R.color.green : R.color.red));
            steps.setText(String.valueOf(currentSteps));
            float barStep = ((float) currentSteps / goalStep) * 100;
            double totalDistanceMeters = currentSteps * averageStrideLengthMeters;
            double totalDistanceKm = totalDistanceMeters / 1000;

            DecimalFormat df = new DecimalFormat("#.##");
            String formattedM = df.format(totalDistanceMeters);
            String formattedKm = df.format(totalDistanceKm);

            totalDistance.setText(totalDistanceMeters >= 1000 ? formattedKm + " KM" : formattedM + " M");
            progressBar.setProgress((int) barStep);
        }
    }

    private void resetSteps() {
        steps.setOnClickListener(v -> Toast.makeText(StepTrackingActivity.this, "LONG PRESS TO RESET STEPS", Toast.LENGTH_SHORT).show());

        steps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                previewTotalSteps = totalSteps;
                steps.setText("0");
                progressBar.setProgress(0);
                saveData();
                return true;
            }

            private void saveData() {
                SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("key1", previewTotalSteps);
                editor.apply();
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        previewTotalSteps = sharedPreferences.getInt("key1", 0);
    }

    private void saveDailySteps(int day, int stepCount) {
        SharedPreferences sharedPreferences = getSharedPreferences("daily_steps", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("day" + day, stepCount);
        editor.apply();
    }

    private int loadDailySteps(int day) {
        SharedPreferences sharedPreferences = getSharedPreferences("daily_steps", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("day" + day, 0);
    }

    private void displaySevenDayRecord() {
        for (int i = 1; i <= 7; i++) {
            int stepCount = loadDailySteps(i);
            Log.d("StepTrackingActivityDebug", stepCount + " DisplaySaveDayRecord for Day " + i);
            // Display stepCount for day i in your UI
            // You can use TextViews, a ListView, or any other UI component to display the data
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
