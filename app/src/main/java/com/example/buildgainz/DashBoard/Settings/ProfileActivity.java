package com.example.buildgainz.DashBoard.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.DashBoard.DashBoardActivity;
import com.example.buildgainz.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
    private FlexboxLayout genderFlexBox;
    private FlexboxLayout levelFlexBox;
    private FlexboxLayout goalsFlexBox;
    Toolbar toolbar;

    TextView changeProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);
        changeProfilePic =findViewById(R.id.changeProfilePic);
        changeProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,ChangeProfilePicActivity.class);
                startActivity(intent);
            }
        });




        genderFlexBox = findViewById(R.id.genderFlexboxLayout);
        levelFlexBox = findViewById(R.id.levelFlexboxLayout);
        goalsFlexBox = findViewById(R.id.goalsFlexboxLayout);

        setupGenderRadioButtons();
        setupLevelRadioButtons();
        setupGoalsRadioButtons();
        loadProfileDataFromFirebase();
        setupSaveButton();



    }


    private void setupSaveButton() {
        Button saveButton = findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(v -> saveRadioGroupValues());
    }

    private void setupGenderRadioButtons() {
        RadioButton maleRadioButton = findViewById(R.id.maleRadioButton);
        RadioButton femaleRadioButton = findViewById(R.id.femaleRadioButton);


        maleRadioButton.setOnClickListener(this::onGenderRadioButtonClicked);
        femaleRadioButton.setOnClickListener(this::onGenderRadioButtonClicked);
    }

    private void onGenderRadioButtonClicked(View view) {
        RadioButton selectedRadioButton = (RadioButton) view;
        clearGenderRadioButtonsExcept(selectedRadioButton.getId());
    }

    private void clearGenderRadioButtonsExcept(int selectedId) {
        RadioButton maleRadioButton = findViewById(R.id.maleRadioButton);
        RadioButton femaleRadioButton = findViewById(R.id.femaleRadioButton);


        if (selectedId != maleRadioButton.getId()) {
            maleRadioButton.setChecked(false);
        }

        if (selectedId != femaleRadioButton.getId()) {
            femaleRadioButton.setChecked(false);
        }

    }

    private void setupLevelRadioButtons() {
        RadioButton beginnerRadioButton = findViewById(R.id.beginnerRadioButton);
        RadioButton intermediateRadioButton = findViewById(R.id.intermediateRadioButton);
        RadioButton advanceRadioButton = findViewById(R.id.advanceRadioButton);

        beginnerRadioButton.setOnClickListener(this::onLevelRadioButtonClicked);
        intermediateRadioButton.setOnClickListener(this::onLevelRadioButtonClicked);
        advanceRadioButton.setOnClickListener(this::onLevelRadioButtonClicked);
    }

    private void setupGoalsRadioButtons() {
        RadioButton losingWeightRadioButton = findViewById(R.id.losingWeightRadioButton);
        RadioButton buildMuscleRadioButton = findViewById(R.id.buildMuscleRadioButton);
        RadioButton bodyRecompRadioButton = findViewById(R.id.bodyRecompRadioButton);

        losingWeightRadioButton.setOnClickListener(this::onGoalsRadioButtonClicked);
        buildMuscleRadioButton.setOnClickListener(this::onGoalsRadioButtonClicked);
        bodyRecompRadioButton.setOnClickListener(this::onGoalsRadioButtonClicked);
    }

    private void onLevelRadioButtonClicked(View view) {
        RadioButton selectedRadioButton = (RadioButton) view;
        clearLevelRadioButtonsExcept(selectedRadioButton.getId());
    }

    private void clearLevelRadioButtonsExcept(int selectedId) {
        RadioButton beginnerRadioButton = findViewById(R.id.beginnerRadioButton);
        RadioButton intermediateRadioButton = findViewById(R.id.intermediateRadioButton);
        RadioButton advanceRadioButton = findViewById(R.id.advanceRadioButton);

        if (selectedId != beginnerRadioButton.getId()) {
            beginnerRadioButton.setChecked(false);
        }

        if (selectedId != intermediateRadioButton.getId()) {
            intermediateRadioButton.setChecked(false);
        }

        if (selectedId != advanceRadioButton.getId()) {
            advanceRadioButton.setChecked(false);
        }
    }

    private void onGoalsRadioButtonClicked(View view) {
        RadioButton selectedRadioButton = (RadioButton) view;
        clearGoalsRadioButtonsExcept(selectedRadioButton.getId());
    }

    private void clearGoalsRadioButtonsExcept(int selectedId) {
        RadioButton losingWeightRadioButton = findViewById(R.id.losingWeightRadioButton);
        RadioButton buildMuscleRadioButton = findViewById(R.id.buildMuscleRadioButton);
        RadioButton bodyRecompRadioButton = findViewById(R.id.bodyRecompRadioButton);

        if (selectedId != losingWeightRadioButton.getId()) {
            losingWeightRadioButton.setChecked(false);
        }

        if (selectedId != buildMuscleRadioButton.getId()) {
            buildMuscleRadioButton.setChecked(false);
        }

        if (selectedId != bodyRecompRadioButton.getId()) {
            bodyRecompRadioButton.setChecked(false);
        }
    }


    private void loadProfileDataFromFirebase() {


        DatabaseReference genderRef = databaseReference.child("gender");
        DatabaseReference levelRef = databaseReference.child("level");
        DatabaseReference goalRef = databaseReference.child("goal");


        genderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gender = snapshot.getValue(String.class);
                if (gender != null) {
                    selectRadioButtonFromGroup(genderFlexBox, gender);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });

        levelRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String level = snapshot.getValue(String.class);
                if (level != null) {
                    selectRadioButtonFromGroup(levelFlexBox, level);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });

        goalRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String goal = snapshot.getValue(String.class);
                if (goal != null) {
                    selectRadioButtonFromGroup(goalsFlexBox, goal);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled if needed
            }
        });
    }

    private void saveRadioGroupValues() {
        String selectedGender = getSelectedRadioButtonText(genderFlexBox);
        String selectedLevel = getSelectedRadioButtonText(levelFlexBox);
        String selectedGoal = getSelectedRadioButtonText(goalsFlexBox);

        if (selectedGender == null || selectedLevel == null || selectedGoal == null) {
            Toast.makeText(ProfileActivity.this, "Please select an option from each group before saving.", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference.child("gender").setValue(selectedGender);
        databaseReference.child("level").setValue(selectedLevel);
        databaseReference.child("goal").setValue(selectedGoal);

        Toast.makeText(ProfileActivity.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ProfileActivity.this, DashBoardActivity.class);
        startActivity(intent);
    }

    private String getSelectedRadioButtonText(FlexboxLayout flexboxLayout) {
        for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
            View radioButton = flexboxLayout.getChildAt(i);
            if (radioButton instanceof RadioButton && ((RadioButton) radioButton).isChecked()) {
                return ((RadioButton) radioButton).getText().toString();
            }
        }
        return null;
    }

    private void selectRadioButtonFromGroup(FlexboxLayout flexboxLayout, String value) {
        for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
            View radioButton = flexboxLayout.getChildAt(i);
            if (radioButton instanceof RadioButton) {
                String radioButtonValue = ((RadioButton) radioButton).getText().toString();
                // Uncheck other options
                ((RadioButton) radioButton).setChecked(radioButtonValue.equals(value));
            }
        }
    }
}
