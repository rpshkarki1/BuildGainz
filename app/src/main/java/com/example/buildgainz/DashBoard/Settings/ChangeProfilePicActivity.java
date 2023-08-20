package com.example.buildgainz.DashBoard.Settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ChangeProfilePicActivity extends AppCompatActivity {
    private ImageView imageViewUpload;

    private static final int PICK_IMAGE_REQUEST= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_pic);


        imageViewUpload = findViewById(R.id.imageViewProfilePic);
        Button buttonUploadPicChoose =findViewById(R.id.choosePicture);
        Button buttonUploadProfilePic =  findViewById(R.id.uploadPicture);
        FirebaseAuth authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("DisplayPics");

        Uri uri = Objects.requireNonNull(firebaseUser).getPhotoUrl();
        Picasso.get().load(uri).resize(50, 50).centerCrop().into(imageViewUpload);


        buttonUploadPicChoose.setOnClickListener(v -> openFileChooser());

        Toolbar toolbar = findViewById(R.id.toolbarChangePic);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uriImage = data.getData();
            imageViewUpload.setImageURI(uriImage);
        }
    }


    //Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}