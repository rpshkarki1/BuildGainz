package com.example.buildgainz;

import static com.example.buildgainz.R.layout;
import static com.example.buildgainz.R.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

public class DashBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton imageButton,profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_dash_board);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileButton = findViewById(R.id.profileBtn);
        profileButton.setOnClickListener(v -> startActivity(new Intent(DashBoardActivity.this,ProfileActivity.class)));

        imageButton = findViewById(R.id.settingBtn);
        imageButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(DashBoardActivity.this, imageButton);
            popupMenu.getMenuInflater().inflate(menu.setting_menu, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(item -> {

                int id = item.getItemId();

                if (id == R.id.item1) {
                    Toast.makeText(DashBoardActivity.this, "THis is it", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (id == R.id.item2){
                    Toast.makeText(DashBoardActivity.this, "This is 2", Toast.LENGTH_SHORT).show();
                    return true;
                }
                throw new IllegalStateException("Unexpected value: " + id);

            });
            popupMenu.show();

        });
    }
}
