package com.example.buildgainz.DashBoard.ExercisePlan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildgainz.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter < ExerciseAdapter.ExerciseViewHolder > {

    private final RecyclerViewInterface recyclerViewInterface;
    private  List < Exercise > exercises;
    Context context;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public ExerciseAdapter ( Context context , List < Exercise > exercises , RecyclerViewInterface recyclerViewInterface ) {
        this.context = context;
        this.exercises = exercises;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    public void setSelectedExercise(int position) {
        if (selectedPosition != position) {
            int previousSelected = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);
        }
    }
    public void setExercises ( List < Exercise > exercises ) {
        this.exercises = exercises;
    }
    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_exercise , parent , false );
        return new ExerciseViewHolder ( view , recyclerViewInterface );
    }

    @Override
    public void onBindViewHolder ( @NonNull ExerciseAdapter.ExerciseViewHolder holder , int position ) {

        Exercise exercise = exercises.get ( position );
        holder.bind ( exercise );
    }

    @Override
    public int getItemCount ( ) {
        return exercises.size ( );
    }

    private String formatListToString ( List < String > list ) {
        StringBuilder stringBuilder = new StringBuilder ( );
        for (String item : list) {
            stringBuilder.append ( item ).append ( "," ); // Customize the formatting as needed
        }
        if (stringBuilder.length ( ) > 2) {
            stringBuilder.setLength ( stringBuilder.length ( ) - 2 );  // Remove the trailing comma and space
        }

        return stringBuilder.toString ( );
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {


        private final TextView exerciseNameTextView;
        private final TextView forceTextView;
        private final TextView levelTextView;
        private final TextView equipmentTextView;
        private final TextView primaryMusclesTextView;
        private final ImageView exerciseImageView;


        public ExerciseViewHolder ( @NonNull View itemView , RecyclerViewInterface recyclerViewInterface ) {
            super ( itemView );
            exerciseImageView = itemView.findViewById ( R.id.exerciseImageView );
            exerciseNameTextView = itemView.findViewById ( R.id.name );
            forceTextView = itemView.findViewById ( R.id.force );
            levelTextView = itemView.findViewById ( R.id.level );
            equipmentTextView = itemView.findViewById ( R.id.equipment );
            primaryMusclesTextView = itemView.findViewById ( R.id.primeMuscle );

            itemView.setOnClickListener ( v -> {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            } );
        }


        @SuppressLint ( "SetTextI18n" )
        public void bind ( Exercise exercise ) {
            exerciseNameTextView.setText ( exercise.getName ( ) );
            forceTextView.setText ( "FORCE: ".concat ( exercise.getForce ( ).toUpperCase ( ) ) );
            levelTextView.setText ( "LEVEL: ".concat ( exercise.getLevel ( ).toUpperCase ( ) ) );
            equipmentTextView.setText ( "EQUIPMENT: ".concat ( exercise.getEquipment ( ).toUpperCase ( ) ) );
            primaryMusclesTextView.setText ( "PRIMARY MUSCLE: ".concat ( formatListToString ( exercise.getPrimaryMuscles ( ) ).toUpperCase ( ) ) );

            // Load and set the first exercise image here

            try {
                AssetManager assetManager = context.getAssets ( );
                String[] assetsList = assetManager.list ( "exercises_img/" + exercise.getImageSubdirectory () );

                if (assetsList != null && assetsList.length >= 2) {
                    String imagePath1 = "exercises_img/" + exercise.getImageSubdirectory () + "/" + assetsList[0];

                    Log.d ( "ExerciseViewActivity" , "Loading image 1: " + imagePath1 );
                    InputStream inputStream1 = assetManager.open ( imagePath1 );
                    Drawable drawable1 = Drawable.createFromStream ( inputStream1 , null );


                    exerciseImageView.setImageDrawable ( drawable1 );


                    inputStream1.close ( );
                } else {
                    Log.e ( "ExerciseViewActivity" , "Insufficient assets found in directory." );
                }
            } catch ( IOException ex ) {
                throw new RuntimeException ( ex );
            }
        }

    }
}



