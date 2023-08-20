package com.example.buildgainz.DashBoard.Settings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.buildgainz.DashBoard.DashBoardActivity;
import com.example.buildgainz.LoginPage.SignUpPage.ReadWriteUserDetails;
import com.example.buildgainz.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance ( ).getReference ( "Registered Users" );
    Toolbar toolbar;
    String fullName;
    String email;
    ImageView imageView;
    TextView changeProfilePic, yourEmailProfile, fullNameProfile, welcomeUser;
    FirebaseAuth authProfile;
    FirebaseUser firebaseUser;
    String displayName;
    private FlexboxLayout genderFlexBox;
    private FlexboxLayout levelFlexBox;
    private FlexboxLayout goalsFlexBox;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );

        toolbar = findViewById ( R.id.toolbarProfile );


        authProfile = FirebaseAuth.getInstance ( );
        firebaseUser = authProfile.getCurrentUser ( );

        yourEmailProfile = findViewById ( R.id.yourEmailProfile );
        fullNameProfile = findViewById ( R.id.fullNameProfile );
        welcomeUser = findViewById ( R.id.welcomeUser );
        imageView = findViewById ( R.id.profilePic );
        changeProfilePic = findViewById ( R.id.changeProfilePic );

        genderFlexBox = findViewById ( R.id.genderFlexboxLayout );
        levelFlexBox = findViewById ( R.id.levelFlexboxLayout );
        goalsFlexBox = findViewById ( R.id.goalsFlexboxLayout );


        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        setupGenderRadioButtons ( );
        setupLevelRadioButtons ( );
        setupGoalsRadioButtons ( );
        loadProfileDataFromFirebase ( );
        setupSaveButton ( );


        // Check if the app has the necessary permissions.
        if ( ContextCompat.checkSelfPermission ( this , Manifest.permission.GET_ACCOUNTS ) != PackageManager.PERMISSION_GRANTED ) {
            // The app does not have the necessary permissions.
            // Request the permissions.
            ActivityCompat.requestPermissions ( this , new String[]{Manifest.permission.GET_ACCOUNTS} , 1 );
        } else {
            // The app has the necessary permissions.
            // Get the display name from the Firebase server.
            displayName = firebaseUser.getDisplayName ( );
        }


        if ( firebaseUser != null ) {
            showUserProfile ( firebaseUser );
        } else {
            Toast.makeText ( ProfileActivity.this , "Something went wrong. Please try again later." , Toast.LENGTH_SHORT ).show ( );

        }


        changeProfilePic.setOnClickListener ( v -> {
            Intent intent = new Intent ( ProfileActivity.this , ChangeProfilePicActivity.class );
            startActivity ( intent );
        } );

        imageView.setOnClickListener ( v -> {
            Intent intent = new Intent ( ProfileActivity.this , ChangeProfilePicActivity.class );
            startActivity ( intent );
        } );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check if the permission being requested is "GET_ACCOUNTS"
        if (requestCode == 1 && permissions.length > 0 && permissions[0].equals(Manifest.permission.GET_ACCOUNTS)) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Now you can retrieve the user's display name.
                displayName = firebaseUser.getDisplayName();
                // Other code to handle the display name
            } else {
                // Permission has been denied.
                Toast.makeText(this, "The permission to read the profile was denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupSaveButton ( ) {
        Button saveButton = findViewById ( R.id.saveBtn );
        saveButton.setOnClickListener ( v -> saveRadioGroupValues ( ) );
    }

    private void setupGenderRadioButtons ( ) {
        RadioButton maleRadioButton = findViewById ( R.id.maleRadioButton );
        RadioButton femaleRadioButton = findViewById ( R.id.femaleRadioButton );


        maleRadioButton.setOnClickListener ( this::onGenderRadioButtonClicked );
        femaleRadioButton.setOnClickListener ( this::onGenderRadioButtonClicked );
    }

    private void onGenderRadioButtonClicked ( View view ) {
        RadioButton selectedRadioButton = (RadioButton) view;
        clearGenderRadioButtonsExcept ( selectedRadioButton.getId ( ) );
    }

    private void clearGenderRadioButtonsExcept ( int selectedId ) {
        RadioButton maleRadioButton = findViewById ( R.id.maleRadioButton );
        RadioButton femaleRadioButton = findViewById ( R.id.femaleRadioButton );


        if ( selectedId != maleRadioButton.getId ( ) ) {
            maleRadioButton.setChecked ( false );
        }

        if ( selectedId != femaleRadioButton.getId ( ) ) {
            femaleRadioButton.setChecked ( false );
        }

    }

    private void setupLevelRadioButtons ( ) {
        RadioButton beginnerRadioButton = findViewById ( R.id.beginnerRadioButton );
        RadioButton intermediateRadioButton = findViewById ( R.id.intermediateRadioButton );
        RadioButton advanceRadioButton = findViewById ( R.id.advanceRadioButton );

        beginnerRadioButton.setOnClickListener ( this::onLevelRadioButtonClicked );
        intermediateRadioButton.setOnClickListener ( this::onLevelRadioButtonClicked );
        advanceRadioButton.setOnClickListener ( this::onLevelRadioButtonClicked );
    }

    private void setupGoalsRadioButtons ( ) {
        RadioButton losingWeightRadioButton = findViewById ( R.id.losingWeightRadioButton );
        RadioButton buildMuscleRadioButton = findViewById ( R.id.buildMuscleRadioButton );
        RadioButton bodyRecompRadioButton = findViewById ( R.id.bodyRecompRadioButton );

        losingWeightRadioButton.setOnClickListener ( this::onGoalsRadioButtonClicked );
        buildMuscleRadioButton.setOnClickListener ( this::onGoalsRadioButtonClicked );
        bodyRecompRadioButton.setOnClickListener ( this::onGoalsRadioButtonClicked );
    }

    private void onLevelRadioButtonClicked ( View view ) {
        RadioButton selectedRadioButton = (RadioButton) view;
        clearLevelRadioButtonsExcept ( selectedRadioButton.getId ( ) );
    }

    private void clearLevelRadioButtonsExcept ( int selectedId ) {
        RadioButton beginnerRadioButton = findViewById ( R.id.beginnerRadioButton );
        RadioButton intermediateRadioButton = findViewById ( R.id.intermediateRadioButton );
        RadioButton advanceRadioButton = findViewById ( R.id.advanceRadioButton );

        if ( selectedId != beginnerRadioButton.getId ( ) ) {
            beginnerRadioButton.setChecked ( false );
        }

        if ( selectedId != intermediateRadioButton.getId ( ) ) {
            intermediateRadioButton.setChecked ( false );
        }

        if ( selectedId != advanceRadioButton.getId ( ) ) {
            advanceRadioButton.setChecked ( false );
        }
    }

    private void onGoalsRadioButtonClicked ( View view ) {
        RadioButton selectedRadioButton = (RadioButton) view;
        clearGoalsRadioButtonsExcept ( selectedRadioButton.getId ( ) );
    }

    private void clearGoalsRadioButtonsExcept ( int selectedId ) {
        RadioButton losingWeightRadioButton = findViewById ( R.id.losingWeightRadioButton );
        RadioButton buildMuscleRadioButton = findViewById ( R.id.buildMuscleRadioButton );
        RadioButton bodyRecompRadioButton = findViewById ( R.id.bodyRecompRadioButton );

        if ( selectedId != losingWeightRadioButton.getId ( ) ) {
            losingWeightRadioButton.setChecked ( false );
        }

        if ( selectedId != buildMuscleRadioButton.getId ( ) ) {
            buildMuscleRadioButton.setChecked ( false );
        }

        if ( selectedId != bodyRecompRadioButton.getId ( ) ) {
            bodyRecompRadioButton.setChecked ( false );
        }
    }


    private void loadProfileDataFromFirebase ( ) {
        authProfile = FirebaseAuth.getInstance ( );
        FirebaseUser firebaseUser = authProfile.getCurrentUser ( );
        DatabaseReference genderRef = databaseReference.child ( "gender" );
        DatabaseReference levelRef = databaseReference.child ( "level" );
        DatabaseReference goalRef = databaseReference.child ( "goal" );

        // Load user profile picture using Picasso
        if ( firebaseUser != null && firebaseUser.getPhotoUrl ( ) != null ) {
            Uri photoUri = firebaseUser.getPhotoUrl ( );//After user has uploaded set User PP
            Picasso.get ( ).load ( photoUri ).into ( imageView ); //Loading uri to ImageView
        }


        genderRef.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot snapshot ) {
                String gender = snapshot.getValue ( String.class );
                if ( gender != null ) {
                    selectRadioButtonFromGroup ( genderFlexBox , gender );
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError error ) {
                // Handle onCancelled if needed
            }
        } );

        levelRef.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot snapshot ) {
                String level = snapshot.getValue ( String.class );
                if ( level != null ) {
                    selectRadioButtonFromGroup ( levelFlexBox , level );
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError error ) {
                // Handle onCancelled if needed
            }
        } );

        goalRef.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot snapshot ) {
                String goal = snapshot.getValue ( String.class );
                if ( goal != null ) {
                    selectRadioButtonFromGroup ( goalsFlexBox , goal );
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError error ) {
                // Handle onCancelled if needed
            }
        } );
    }

    private void showUserProfile ( FirebaseUser firebaseUser ) {
        String userId = firebaseUser.getUid ( );


        //Extracting User reference from firebase
        DatabaseReference reference = FirebaseDatabase.getInstance ( ).getReference ( "Registered Users" );
        reference.child ( userId ).addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange ( @NonNull DataSnapshot snapshot ) {
                ReadWriteUserDetails writeUserDetails = snapshot.getValue ( ReadWriteUserDetails.class );
                if ( writeUserDetails != null ) {
                    fullName = writeUserDetails.fullName;
                    email = firebaseUser.getEmail ( );

                    welcomeUser.setText ( "Welcome, " + fullName + "!" );
                    fullNameProfile.setText ( fullName );
                    yourEmailProfile.setText ( email );
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError error ) {
                Toast.makeText ( ProfileActivity.this , "Something went wrong!" , Toast.LENGTH_SHORT ).show ( );

            }
        } );

    }

    private void saveRadioGroupValues ( ) {
        String selectedGender = getSelectedRadioButtonText ( genderFlexBox );
        String selectedLevel = getSelectedRadioButtonText ( levelFlexBox );
        String selectedGoal = getSelectedRadioButtonText ( goalsFlexBox );

        if ( selectedGender == null || selectedLevel == null || selectedGoal == null ) {
            Toast.makeText ( ProfileActivity.this , "Please select an option from each group before saving." , Toast.LENGTH_SHORT ).show ( );
            return;
        }

        databaseReference.child ( "gender" ).setValue ( selectedGender );
        databaseReference.child ( "level" ).setValue ( selectedLevel );
        databaseReference.child ( "goal" ).setValue ( selectedGoal );

        Toast.makeText ( ProfileActivity.this , "Data saved successfully." , Toast.LENGTH_SHORT ).show ( );

        Intent intent = new Intent ( ProfileActivity.this , DashBoardActivity.class );
        startActivity ( intent );
    }

    private String getSelectedRadioButtonText ( FlexboxLayout flexboxLayout ) {
        for (int i = 0; i < flexboxLayout.getChildCount ( ); i++) {
            View radioButton = flexboxLayout.getChildAt ( i );
            if ( radioButton instanceof RadioButton && ((RadioButton) radioButton).isChecked ( ) ) {
                return ((RadioButton) radioButton).getText ( ).toString ( );
            }
        }
        return null;
    }

    private void selectRadioButtonFromGroup ( FlexboxLayout flexboxLayout , String value ) {
        for (int i = 0; i < flexboxLayout.getChildCount ( ); i++) {
            View radioButton = flexboxLayout.getChildAt ( i );
            if ( radioButton instanceof RadioButton ) {
                String radioButtonValue = ((RadioButton) radioButton).getText ( ).toString ( );
                // Uncheck other options
                ((RadioButton) radioButton).setChecked ( radioButtonValue.equals ( value ) );
            }
        }
    }

    //Back Button
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if ( item.getItemId ( ) == android.R.id.home ) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}
