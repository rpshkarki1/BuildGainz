package com.example.buildgainz.DashBoard.Tracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;

import java.util.Objects;

public class StepTrackingActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor stepSensor;
    private  int totalSteps = 0;
    private  int previewTotalSteps = 0 ;
    private ProgressBar progressBar;
    private TextView steps;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_step_tracking );

        progressBar = findViewById ( R.id.progressBar );
        steps = findViewById ( R.id.steps );

        sensorManager = (SensorManager) getSystemService ( SENSOR_SERVICE );
        stepSensor = sensorManager.getDefaultSensor ( Sensor.TYPE_STEP_COUNTER );
        Toolbar toolbar = findViewById ( R.id.toolbarTracking);
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );
    }

    @Override
    protected void onResume ( ) {
        super.onResume ( );

        if(stepSensor == null){
            Toast.makeText ( this , "This device has no sensor." , Toast.LENGTH_SHORT ).show ( );

        }else {
            sensorManager.registerListener ( this,stepSensor,SensorManager.SENSOR_DELAY_NORMAL   );

        }

    }

    @Override
    protected void onPause ( ) {
        super.onPause ( );

        sensorManager.unregisterListener ( this );

    }

    @Override
    public void onSensorChanged ( SensorEvent event ) {

        if(event.sensor.getType () == Sensor.TYPE_STEP_COUNTER){
            totalSteps = (int) event.values[0];
            int currentSteps = totalSteps-previewTotalSteps;
            steps.setText ( String.valueOf ( currentSteps ) );

            progressBar.setProgress ( currentSteps );
        }
    }

    private void resetSteps (){
        steps.setOnClickListener ( v -> Toast.makeText ( StepTrackingActivity.this , "LONG PRESS TO RESET STEPS" , Toast.LENGTH_SHORT ).show ( ) );

        steps.setOnLongClickListener ( new View.OnLongClickListener ( ) {
            @Override
            public boolean onLongClick ( View v ) {
                previewTotalSteps = totalSteps;
                steps.setText ( "0" );
                progressBar.setProgress ( 0 );
                saveData();
                return true;

            }

            private void saveData ( ) {
                SharedPreferences sharedPreferences = getSharedPreferences ( "myPref", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit ();
                editor.putString ( "key1",String.valueOf ( previewTotalSteps ) );
                editor.apply ();

            }
        } );
    }


    private  void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences ( "myPref",Context.MODE_PRIVATE );
        previewTotalSteps = (int) sharedPreferences.getFloat ( "key1",0f );
    }
    @Override
    public void onAccuracyChanged ( Sensor sensor , int accuracy ) {

    }

    @Override
    public void onPointerCaptureChanged ( boolean hasCapture ) {
        super.onPointerCaptureChanged ( hasCapture );
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if ( item.getItemId ( ) == android.R.id.home ) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}