package com.example.buildgainz.DashBoard.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.buildgainz.DashBoard.DashBoardActivity;
import com.example.buildgainz.LoginPage.LoginPageActivity;
import com.example.buildgainz.R;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class DeleteUserActivity extends AppCompatActivity {
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private EditText editTextUserPwd;
    private TextView textViewAuthenticated;
    private String userPwd;
    private Button buttonReAuthenticate,buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        Toolbar toolbar = findViewById(R.id.toolbarChangePwd);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        editTextUserPwd = findViewById(R.id.currentPassEditText);
        textViewAuthenticated = findViewById(R.id.changePwdAuthenticate);
        buttonReAuthenticate = findViewById(R.id.authenticateBtn);
        buttonDelete = findViewById(R.id.deleteAccBtn);
        buttonDelete.setEnabled(false);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        if (firebaseUser == null) {
            Toast.makeText(DeleteUserActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DeleteUserActivity.this, DashBoardActivity.class));
            finish();
        } else {
            reAuthenticateUser(firebaseUser);
        }

    }

    private void reAuthenticateUser(FirebaseUser firebaseUser) {
        buttonReAuthenticate.setOnClickListener(v -> {
            userPwd = editTextUserPwd.getText().toString();

            if (TextUtils.isEmpty(userPwd)) {
                Toast.makeText(DeleteUserActivity.this, "Password is Empty!!!", Toast.LENGTH_SHORT).show();
                editTextUserPwd.setError("Please enter your current password.");
                editTextUserPwd.requestFocus();
            } else {
                AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(firebaseUser.getEmail()), userPwd);
                firebaseUser.reauthenticate(credential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        editTextUserPwd.setEnabled(false);
                        buttonReAuthenticate.setEnabled(false);
                        buttonDelete.setEnabled(true);


                        textViewAuthenticated.setText(R.string.textViewOfDeleteAuthenticate);

                        buttonDelete.setBackgroundTintList(ContextCompat.getColorStateList(DeleteUserActivity.this, R.color.green));

                        buttonDelete.setOnClickListener(v1 -> showAlertDialogBox());

                    } else {
                        try {
                            throw Objects.requireNonNull(task.getException());

                        } catch (Exception e) {
                            Toast.makeText(DeleteUserActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });


    }
    private void showAlertDialogBox() {

        //Set up for Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUserActivity.this);
        builder.setTitle("Confirm Delete.");
        builder.setMessage("Do you really want to delete your account?");

        //Open Email Apps if User Clicks Continue Btn
        builder.setPositiveButton("Continue", (dialog, which) -> deleteUser(firebaseUser));


        builder.setNegativeButton("Cancel", (dialog, which) -> {
            startActivity(new Intent(DeleteUserActivity.this , DashBoardActivity.class));
            finish();
        });

        //Create AlertDialog
        AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(dialog -> alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red)));


        //Show alertDialog
        alertDialog.show();

    }

    private void deleteUser(FirebaseUser firebaseUser) {
        firebaseUser.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                authProfile.signOut();
                Toast.makeText(DeleteUserActivity.this, "Your account has deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DeleteUserActivity.this,LoginPageActivity.class));
                finish();
            }else {
                try {
                    throw Objects.requireNonNull(task.getException());
                }catch (Exception e){
                    Toast.makeText(DeleteUserActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}