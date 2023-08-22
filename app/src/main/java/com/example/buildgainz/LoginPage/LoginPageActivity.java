package com.example.buildgainz.LoginPage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buildgainz.DashBoard.DashBoardActivity;
import com.example.buildgainz.LoginPage.SignUpPage.SignUpActivity;
import com.example.buildgainz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class LoginPageActivity extends AppCompatActivity {

    private static final String TAG = "LoginPageActivity";
    private EditText emailEditText, passEditText;
    private FirebaseAuth authProfile;
    private ImageView imageViewShowHidePwd;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login_page );

        //When Clicked Sign Up now Button
        TextView signUp = findViewById ( R.id.signUpBtn );
        signUp.setOnClickListener ( v -> {
            Intent intent = new Intent ( LoginPageActivity.this , SignUpActivity.class );
            startActivity ( intent );
        } );

        //When Clicked Forgot Password Button
        TextView forgotPass = findViewById ( R.id.forgotPass );
        forgotPass.setOnClickListener ( v -> {
            Intent intent = new Intent ( LoginPageActivity.this , ForgotPasswordActivity.class );
            startActivity ( intent );
        } );

        emailEditText = findViewById ( R.id.editTextYourEmail );
        passEditText = findViewById ( R.id.editTextYourPassword );
        imageViewShowHidePwd = findViewById ( R.id.imgViewShowHide );
        imageViewShowHidePwd.setImageResource ( R.drawable.ic_hide_pwd );

        //Show hide password using Eye icon using imgView
        imageViewShowHidePwd.setOnClickListener ( v -> {
            if ( passEditText.getTransformationMethod ( ).equals ( HideReturnsTransformationMethod.getInstance ( ) ) ) {

                //if pwd is visible then hides it
                passEditText.setTransformationMethod ( PasswordTransformationMethod.getInstance ( ) );
                //Then Change Icon
                imageViewShowHidePwd.setImageResource ( R.drawable.ic_show_pwd );
            } else {
                passEditText.setTransformationMethod ( HideReturnsTransformationMethod.getInstance ( ) );
                imageViewShowHidePwd.setImageResource ( R.drawable.ic_hide_pwd );

            }
        } );


        authProfile = FirebaseAuth.getInstance ( );

        Button buttonSignIn = findViewById ( R.id.signInBtn );
        //When Clicked Sign In Button
        buttonSignIn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                String textEmail = emailEditText.getText ( ).toString ( );
                String textPassword = passEditText.getText ( ).toString ( );

                if ( TextUtils.isEmpty ( textEmail ) ) {
                    Toast.makeText ( LoginPageActivity.this , "Please Enter Your Email." , Toast.LENGTH_SHORT ).show ( );
                    emailEditText.setError ( "Email is required!" );
                    emailEditText.requestFocus ( );
                } else if ( ! Patterns.EMAIL_ADDRESS.matcher ( textEmail ).matches ( ) ) {
                    Toast.makeText ( LoginPageActivity.this , "Please re-Enter your Email." , Toast.LENGTH_SHORT ).show ( );
                    emailEditText.setError ( "Valid Email is required!" );
                    emailEditText.requestFocus ( );
                } else if ( TextUtils.isEmpty ( textPassword ) ) {
                    Toast.makeText ( LoginPageActivity.this , "Please enter your Password." , Toast.LENGTH_SHORT ).show ( );
                    passEditText.setError ( "Password is required!" );
                    passEditText.requestFocus ( );
                } else {
                    loginUser ( textEmail , textPassword );
                }

            }

            private void loginUser ( String email , String password ) {
                authProfile.signInWithEmailAndPassword ( email , password ).addOnCompleteListener ( LoginPageActivity.this , task -> {
                    if ( task.isSuccessful ( ) ) {
                        //Get instance of User
                        FirebaseUser firebaseUser = authProfile.getCurrentUser ( );

                        //Check if email is verified before user can access their profile
                        if ( firebaseUser != null && firebaseUser.isEmailVerified ( ) ) {
                            Bundle extras = getIntent ( ).getExtras ( );


                            Intent intent = new Intent ( LoginPageActivity.this , DashBoardActivity.class );

                            startActivity ( intent );
                        } else {
                            assert firebaseUser != null;
                            firebaseUser.sendEmailVerification ( );
                            authProfile.signOut ( );
                            showAlertDialogBox ( );
                        }


                    } else {

                        try {
                            throw Objects.requireNonNull ( task.getException ( ) );
                        } catch ( FirebaseAuthInvalidUserException e ) {
                            emailEditText.setError ( "User does not exists or is no longer valid. Please sign up again." );
                            emailEditText.requestFocus ( );
                        } catch ( FirebaseAuthInvalidCredentialsException e ) {
                            emailEditText.setError ( "Invalid credentials. Please check again and re-Enter." );
                            passEditText.setError ( "Invalid credentials. Please check again and re-Enter." );

                            emailEditText.requestFocus ( );
                            passEditText.requestFocus ( );
                        } catch ( Exception e ) {
                            Log.e ( TAG , Objects.requireNonNull ( e.getMessage ( ) ) );
                            Toast.makeText ( LoginPageActivity.this , e.getMessage ( ) , Toast.LENGTH_SHORT ).show ( );
                        }
                    }
                } );
            }
        } );
    }


    private void showAlertDialogBox ( ) {

        //Set up for Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder ( LoginPageActivity.this );
        builder.setTitle ( "Email is not Verified!" );
        builder.setMessage ( "Please verify your email now. You can't login without verifying email." );

        //Open Email Apps if User Clicks Continue Btn
        builder.setPositiveButton ( "Continue" , ( dialog , which ) -> {
            Intent intent = new Intent ( Intent.ACTION_MAIN );
            intent.addCategory ( Intent.CATEGORY_APP_EMAIL );
            intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK ); //To Email App in new Window
            startActivity ( intent );
        } );

        //Create AlertDialog
        AlertDialog alertDialog = builder.create ( );


        //Show alertDialog
        alertDialog.show ( );

    }

    @Override
    protected void onStart ( ) {
        super.onStart ( );
        FirebaseUser currentUser = authProfile.getCurrentUser ( );

        if ( currentUser != null ) {
            // Check if the user is verified in the Firebase database
            DatabaseReference userRef = FirebaseDatabase.getInstance ( ).getReference ( "users" ).child ( currentUser.getUid ( ) );

            userRef.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                @Override
                public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                    if ( dataSnapshot.exists ( ) && Boolean.TRUE.equals ( dataSnapshot.child ( "verified" ).getValue ( Boolean.class ) )) {
                        // User is verified, proceed to dashboard
                        Toast.makeText ( LoginPageActivity.this , "Already Logged In!" , Toast.LENGTH_SHORT ).show ( );
                        startActivity ( new Intent ( LoginPageActivity.this , DashBoardActivity.class ) );
                        finish ( );
                    }
                }

                @Override
                public void onCancelled ( @NonNull DatabaseError error ) {

                }
            } );
        }
    }


}