package com.example.buildgainz.DashBoard;

import static com.example.buildgainz.R.layout;
import static com.example.buildgainz.R.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.DashBoard.Profile.ProfileActivity;
import com.example.buildgainz.DashBoard.Settings.ChangePasswordActivity;
import com.example.buildgainz.DashBoard.Settings.DeleteUserActivity;
import com.example.buildgainz.DashBoard.ExercisePlan.ExercisesActivity;
import com.example.buildgainz.LoginPage.LoginPageActivity;
import com.example.buildgainz.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class DashBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton settingButton;
    ShapeableImageView profileButton;
    RelativeLayout exercisePlan;
    FirebaseAuth authProfile;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( layout.activity_dash_board );

        toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        settingButton = findViewById ( R.id.settingBtn );
        profileButton = findViewById ( R.id.profileBtn );
        exercisePlan = findViewById ( R.id.exerCardView );
        authProfile = FirebaseAuth.getInstance ( );
        FirebaseUser firebaseUser = authProfile.getCurrentUser ( );
        if ( firebaseUser != null && firebaseUser.getPhotoUrl ( ) != null ) {
            Uri photoUri = firebaseUser.getPhotoUrl ( );//After user has uploaded set User PP
            Picasso.get ( ).load ( photoUri ).into ( profileButton ); //Loading uri to ImageView


        }
        profileButton.setOnClickListener ( v -> {


            Intent intent = new Intent ( DashBoardActivity.this , ProfileActivity.class );

            startActivity ( intent );

        } );

        settingButton.setOnClickListener ( v -> {
            PopupMenu popupMenu = new PopupMenu ( DashBoardActivity.this , settingButton );
            popupMenu.getMenuInflater ( ).inflate ( menu.setting_menu , popupMenu.getMenu ( ) );
            popupMenu.show ( );

            popupMenu.setOnMenuItemClickListener ( item -> {

                int id = item.getItemId ( );

                if ( id == R.id.changePass ) {
                    startActivity ( new Intent ( DashBoardActivity.this , ChangePasswordActivity.class ) );
                } else if ( id == R.id.deleteAcc ) {
                    startActivity ( new Intent ( DashBoardActivity.this , DeleteUserActivity.class ) );
                } else if ( id == R.id.logout ) {//create logout with DialogBox
                    showLogoutDialog ( );
                    return true;
                } else {
                    Toast.makeText ( DashBoardActivity.this , "Something went wrong" , Toast.LENGTH_SHORT ).show ( );
                }
                return DashBoardActivity.super.onOptionsItemSelected ( item );
            } );
        } );

        exercisePlan.setOnClickListener ( v -> startActivity ( new Intent ( DashBoardActivity.this , ExercisesActivity.class ) ) );


    }

    private void showLogoutDialog ( ) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder ( this );
        alertDialogBuilder.setTitle ( "Logout" );
        alertDialogBuilder.setMessage ( "Are you sure you want to logout?" );
        alertDialogBuilder.setPositiveButton ( "Yes" , ( dialogInterface , i ) -> {
            FirebaseAuth.getInstance ( ).signOut ( );
            Intent intent = new Intent ( DashBoardActivity.this , LoginPageActivity.class );
            intent.addFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity ( intent );
            finish ( );
        } );
        alertDialogBuilder.setNegativeButton ( "No" , ( dialogInterface , i ) -> {
            // Do nothing or handle cancellation if needed.
        } );


        AlertDialog alertDialog = alertDialogBuilder.create ( );

        alertDialog.setOnShowListener ( dialog -> alertDialog.getButton ( AlertDialog.BUTTON_POSITIVE ).setTextColor ( getResources ( ).getColor ( R.color.red ) ) );

        alertDialog.show ( );
    }
}

