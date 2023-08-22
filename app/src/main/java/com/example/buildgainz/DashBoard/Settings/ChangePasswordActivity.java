package com.example.buildgainz.DashBoard.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.buildgainz.DashBoard.DashBoardActivity;
import com.example.buildgainz.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText yourCrntPass, yourNewPass, againNewPass;
    private TextView verifiedChecker;

    private Button authenticate, updatePass;

    private String crntUserPwd;
    private ImageView imageViewShowHidePwd4, imageViewShowHidePwd5;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_change_password );

        Toolbar toolbar = findViewById ( R.id.toolbarChangePwd );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        imageViewShowHidePwd4 = findViewById ( R.id.imgViewShowHide4 );
        imageViewShowHidePwd4.setImageResource ( R.drawable.ic_hide_pwd );

        //Show hide password using Eye icon using imgView
        imageViewShowHidePwd4.setOnClickListener ( v -> {
            if ( yourNewPass.getTransformationMethod ( ).equals ( HideReturnsTransformationMethod.getInstance ( ) ) ) {

                //if pwd is visible then hides it
                yourNewPass.setTransformationMethod ( PasswordTransformationMethod.getInstance ( ) );
                //Then Change Icon
                imageViewShowHidePwd4.setImageResource ( R.drawable.ic_show_pwd );
            } else {
                yourNewPass.setTransformationMethod ( HideReturnsTransformationMethod.getInstance ( ) );
                imageViewShowHidePwd4.setImageResource ( R.drawable.ic_hide_pwd );

            }
        } );

        imageViewShowHidePwd5 = findViewById ( R.id.imgViewShowHide5 );
        imageViewShowHidePwd5.setImageResource ( R.drawable.ic_hide_pwd );

        //Show hide password using Eye icon using imgView
        imageViewShowHidePwd5.setOnClickListener ( v -> {
            if ( againNewPass.getTransformationMethod ( ).equals ( HideReturnsTransformationMethod.getInstance ( ) ) ) {

                //if pwd is visible then hides it
                againNewPass.setTransformationMethod ( PasswordTransformationMethod.getInstance ( ) );
                //Then Change Icon
                imageViewShowHidePwd5.setImageResource ( R.drawable.ic_show_pwd );
            } else {
                againNewPass.setTransformationMethod ( HideReturnsTransformationMethod.getInstance ( ) );
                imageViewShowHidePwd5.setImageResource ( R.drawable.ic_hide_pwd );

            }
        } );


        yourCrntPass = findViewById ( R.id.yourCurrentPassEditText );
        yourNewPass = findViewById ( R.id.yourNewPassEditText );
        againNewPass = findViewById ( R.id.yourReNewPassEditText );
        verifiedChecker = findViewById ( R.id.changePwdAuthenticate );
        authenticate = findViewById ( R.id.authenticate );
        updatePass = findViewById ( R.id.updatePassBtn );

        //disable newPass & againPass until the user is verified
        yourNewPass.setEnabled ( false );
        againNewPass.setEnabled ( false );
        updatePass.setEnabled ( false );

        FirebaseAuth authProfile = FirebaseAuth.getInstance ( );
        FirebaseUser firebaseUser = authProfile.getCurrentUser ( );

        if ( firebaseUser == null ) {
            Toast.makeText ( ChangePasswordActivity.this , "Something Went Wrong!" , Toast.LENGTH_SHORT ).show ( );
            startActivity ( new Intent ( ChangePasswordActivity.this , DashBoardActivity.class ) );
            finish ( );
        } else {
            reAuthenticateUser ( firebaseUser );
        }


    }

    private void reAuthenticateUser ( FirebaseUser firebaseUser ) {
        authenticate.setOnClickListener ( v -> {
            crntUserPwd = yourCrntPass.getText ( ).toString ( );

            if ( TextUtils.isEmpty ( crntUserPwd ) ) {
                Toast.makeText ( ChangePasswordActivity.this , "Password is Empty!!!" , Toast.LENGTH_SHORT ).show ( );
                yourCrntPass.setError ( "Please enter your current password." );
                yourCrntPass.requestFocus ( );
            } else {
                AuthCredential credential = EmailAuthProvider.getCredential ( Objects.requireNonNull ( firebaseUser.getEmail ( ) ) , crntUserPwd );
                firebaseUser.reauthenticate ( credential ).addOnCompleteListener ( task -> {
                    if ( task.isSuccessful ( ) ) {
                        yourCrntPass.setEnabled ( false );
                        authenticate.setEnabled ( false );
                        yourNewPass.setEnabled ( true );
                        againNewPass.setEnabled ( true );
                        updatePass.setEnabled ( true );

                        verifiedChecker.setText ( R.string.textViewOfAuthenticate );

                        updatePass.setBackgroundTintList ( ContextCompat.getColorStateList ( ChangePasswordActivity.this , R.color.green ) );

                        updatePass.setOnClickListener ( v1 -> changePassword ( firebaseUser ) );

                    } else {
                        try {
                            throw Objects.requireNonNull ( task.getException ( ) );

                        } catch ( Exception e ) {
                            Toast.makeText ( ChangePasswordActivity.this , e.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
                        }
                    }
                } );


            }

        } );


    }

    private void changePassword ( FirebaseUser firebaseUser ) {
        String userPwdNew = yourNewPass.getText ( ).toString ( );
        String userRePwdNew = againNewPass.getText ( ).toString ( );

        if ( TextUtils.isEmpty ( userPwdNew ) ) {
            yourNewPass.setError ( "Please enter your new password" );
            yourNewPass.requestFocus ( );
        } else if ( TextUtils.isEmpty ( userRePwdNew ) ) {
            againNewPass.setError ( "Please enter your new password" );
            againNewPass.requestFocus ( );
        } else if ( ! userPwdNew.matches ( userRePwdNew ) ) {
            yourNewPass.setError ( "Password didn't matched." );
            yourNewPass.requestFocus ( );
            againNewPass.setError ( "Please didn't matched" );
            againNewPass.requestFocus ( );
        } else if ( userPwdNew.matches ( crntUserPwd ) ) {
            yourNewPass.setError ( "New password cannot be matched with your old password" );
            yourNewPass.requestFocus ( );
        } else {
            firebaseUser.updatePassword ( userPwdNew ).addOnCompleteListener ( task -> {
                if ( task.isSuccessful ( ) ) {
                    Toast.makeText ( ChangePasswordActivity.this , "Password has been changed" , Toast.LENGTH_SHORT ).show ( );
                    startActivity ( new Intent ( ChangePasswordActivity.this , DashBoardActivity.class ) );
                    finish ( );
                } else {
                    try {
                        throw Objects.requireNonNull ( task.getException ( ) );
                    } catch ( Exception e ) {
                        Toast.makeText ( ChangePasswordActivity.this , e.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
                    }
                }
            } );
        }
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