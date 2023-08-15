package com.example.buildgainz;

import static com.example.buildgainz.R.layout;
import static com.example.buildgainz.R.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.SplashScreens.SplashScreenActivity;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton imageButton, profileButton;
    FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_dash_board);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileButton = findViewById(R.id.profileBtn);
        profileButton.setOnClickListener(v -> startActivity(new Intent(DashBoardActivity.this, ProfileActivity.class)));

        imageButton = findViewById(R.id.settingBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DashBoardActivity.this, imageButton);
                popupMenu.getMenuInflater().inflate(menu.setting_menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.changePass) {
                            startActivity(new Intent(DashBoardActivity.this, ChangePasswordActivity.class));
                        }else if (id == R.id.deleteAcc) {
                            startActivity(new Intent(DashBoardActivity.this, DeletePasswordActivity.class));
                        }else if (id == R.id.logout) {//create logout with DialogBox
                            authProfile.signOut();
                            Toast.makeText(DashBoardActivity.this, "You are logged out.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DashBoardActivity.this, SplashScreenActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            DashBoardActivity.this.startActivity(intent);
                            DashBoardActivity.this.finish();
                        } else {
                            Toast.makeText(DashBoardActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                        return DashBoardActivity.super.onOptionsItemSelected(item);
                    }
                });
            }
        });
    }
}
