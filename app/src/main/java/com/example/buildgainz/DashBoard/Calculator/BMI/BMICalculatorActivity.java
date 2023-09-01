package com.example.buildgainz.DashBoard.Calculator.BMI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.buildgainz.R;

import java.util.Objects;

public class BMICalculatorActivity extends AppCompatActivity {


    TextView currentHeight;
    TextView currentWeight,currentAge;
    ImageView incrementAge,decrementAge,incrementWeight,decrementWeight;
    SeekBar seekbarForHeight;
    Button calculateBmi;
    RelativeLayout male,female;

    int intWeight=55;
    int intAge=22;
    int currentProgress;
    String intProgress="170";
    String typesOfUser="0";
    String weight2="55";
    String age2="22";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator );

        Toolbar toolbar = findViewById ( R.id.toolbarBMI);
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );

        currentAge=findViewById(R.id.currentAge);
        currentWeight=findViewById(R.id.currentWeight);
        currentHeight=findViewById(R.id.currentHeight);
        incrementAge=findViewById(R.id.incrementAge);
        decrementAge=findViewById(R.id.decrementAge);
        incrementWeight=findViewById(R.id.incrementWeight);
        decrementWeight=findViewById(R.id.decrementWeight);
        calculateBmi=findViewById(R.id.calculateBmi);
        seekbarForHeight=findViewById(R.id.seekbarForHeight);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);



        male.setOnClickListener( v -> {
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            typesOfUser="Male";

        } );


        female.setOnClickListener( v -> {
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalenotfocus));
            typesOfUser="Female";
        } );

        seekbarForHeight.setMax(300);
        seekbarForHeight.setProgress(170);
        seekbarForHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentProgress=progress;
                intProgress=String.valueOf(currentProgress);
                currentHeight.setText(intProgress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        incrementWeight.setOnClickListener( v -> {
            intWeight=intWeight+1;
            weight2=String.valueOf(intWeight);
            currentWeight.setText(weight2);
        } );

        incrementAge.setOnClickListener( v -> {
            intAge=intAge+1;
            age2=String.valueOf(intAge);
            currentAge.setText(age2);
        } );


        decrementAge.setOnClickListener( v -> {
            intAge=intAge-1;
            age2=String.valueOf(intAge);
            currentAge.setText(age2);
        } );


        decrementWeight.setOnClickListener( v -> {

            intWeight=intWeight-1;
            weight2=String.valueOf(intWeight);
            currentWeight.setText(weight2);
        } );



        calculateBmi.setOnClickListener( v -> {

            if(typesOfUser.equals("0"))
            {
                Toast.makeText(getApplicationContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
            }
            else if(intProgress.equals("0"))
            {
                Toast.makeText(getApplicationContext(),"Select Your Height First",Toast.LENGTH_SHORT).show();
            }
            else if(intAge==0 || intAge<0)
            {
                Toast.makeText(getApplicationContext(),"Age is Incorrect",Toast.LENGTH_SHORT).show();
            }

            else if(intWeight==0|| intWeight<0)
            {
                Toast.makeText(getApplicationContext(),"Weight Is Incorrect",Toast.LENGTH_SHORT).show();
            }
            else {

                Intent intent = new Intent(BMICalculatorActivity.this, BMIViewActivity.class);
                intent.putExtra("gender", typesOfUser);
                intent.putExtra("height", intProgress);
                intent.putExtra("weight", weight2);
                intent.putExtra("age", age2);
                startActivity(intent);
                finish ();

            }


        } );


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