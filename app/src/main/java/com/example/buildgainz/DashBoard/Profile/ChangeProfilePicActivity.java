package com.example.buildgainz.DashBoard.Profile;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buildgainz.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ChangeProfilePicActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri uriImage;
    StorageReference storageReference;
    FirebaseAuth authProfile;
    FirebaseUser firebaseUser;
    private ShapeableImageView imageViewUpload;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_change_profile_pic );


        imageViewUpload = findViewById ( R.id.imageViewProfilePic );
        Button buttonUploadPicChoose = findViewById ( R.id.choosePicture );
        Button buttonUploadProfilePic = findViewById ( R.id.uploadPicture );
        authProfile = FirebaseAuth.getInstance ( );
        firebaseUser = authProfile.getCurrentUser ( );

        Toolbar toolbar = findViewById ( R.id.toolbarChangePic );
        setSupportActionBar ( toolbar );
        Objects.requireNonNull ( getSupportActionBar ( ) ).setDisplayHomeAsUpEnabled ( true );


        storageReference = FirebaseStorage.getInstance ( ).getReference ( "DisplayPics" );

        Uri uri = Objects.requireNonNull ( firebaseUser ).getPhotoUrl ( );

        //Set user's current profilePic in ImageView  (Regular URIs).
        Picasso.get ( ).load ( uri ).into ( imageViewUpload );

        //Choose image to upload
        buttonUploadPicChoose.setOnClickListener ( v -> openFileChooser ( ) );

        //Upload image to View in PP
        buttonUploadProfilePic.setOnClickListener ( v -> UploadPic ( ) );

    }

    private void UploadPic ( ) {
        if (uriImage != null) {
            //Save the image with uid of the currently logged User
            StorageReference fileReference = storageReference.child ( Objects.requireNonNull ( authProfile.getCurrentUser ( ) ).getUid ( ) + "." +
                    getFilesExtension ( uriImage ) );

            //Upload image to Fire storage

            fileReference.putFile ( uriImage ).addOnSuccessListener ( taskSnapshot -> fileReference.getDownloadUrl ( ).addOnSuccessListener ( uri -> {
                firebaseUser = authProfile.getCurrentUser ( );

                //Set the profile pic of the user after upload profile pic button
                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder ( ).
                        setPhotoUri ( uri ).build ( );
                firebaseUser.updateProfile ( profileChangeRequest );

                Toast.makeText ( ChangeProfilePicActivity.this , "Uploaded Successfully." , Toast.LENGTH_SHORT ).show ( );
                startActivity ( new Intent ( ChangeProfilePicActivity.this , ProfileActivity.class ) );
                finish ( );

            } ) ).addOnFailureListener ( e -> Toast.makeText ( ChangeProfilePicActivity.this , "Uploaded Unsuccessfully." , Toast.LENGTH_SHORT ).show ( ) );


        }

    }

    private String getFilesExtension ( Uri uri ) {
        //cR provides access to the content model,which allows you to interact with various data i.e. image,videos,audio,etc
        ContentResolver contentResolver = getContentResolver ( );
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton ( );

        return mimeTypeMap.getExtensionFromMimeType ( contentResolver.getType ( uri ) );
    }

    private void openFileChooser ( ) {
        Intent intent = new Intent ( );
        intent.setType ( "image/*" );
        intent.setAction ( Intent.ACTION_GET_CONTENT );
        startActivityForResult ( intent , PICK_IMAGE_REQUEST );
    }

    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult ( requestCode , resultCode , data );
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData ( ) != null) {
            uriImage = data.getData ( );
            imageViewUpload.setImageURI ( uriImage );
        }
    }


    //Back Button
    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == android.R.id.home) {
            onBackPressed ( ); // This will emulate the behavior of the back button
            return true;
        }
        return super.onOptionsItemSelected ( item );
    }
}