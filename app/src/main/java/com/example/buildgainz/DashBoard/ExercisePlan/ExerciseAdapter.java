package com.example.buildgainz.DashBoard.ExercisePlan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
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

    Context context;
    private final List < Exercise > exercises;

    public ExerciseAdapter ( Context context , List < Exercise > exercises ) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.item_exercise , parent , false );
        return new ExerciseViewHolder ( view );
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

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {


        private final TextView exerciseNameTextView;
        private final TextView forceTextView;
        private final TextView levelTextView;
        private final TextView mechanicTextView;
        private final TextView equipmentTextView;
        private final TextView primaryMusclesTextView;
        private final TextView secondaryMusclesTextView;
        private final ImageView exerciseImageView;


        public ExerciseViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            exerciseImageView = itemView.findViewById(R.id.exerciseImageView);
            exerciseNameTextView = itemView.findViewById ( R.id.name );
            forceTextView = itemView.findViewById ( R.id.force );
            levelTextView = itemView.findViewById ( R.id.level );
            mechanicTextView = itemView.findViewById ( R.id.mechanic );
            equipmentTextView = itemView.findViewById ( R.id.equipment );
            primaryMusclesTextView = itemView.findViewById ( R.id.primeMuscle );
            secondaryMusclesTextView = itemView.findViewById ( R.id.secondMuscle );
        }


        public void bind ( Exercise exercise ) {
            exerciseNameTextView.setText ( exercise.getName ( ) );
            forceTextView.setText ( exercise.getForce ( ) );
            levelTextView.setText ( exercise.getLevel ( ) );
            mechanicTextView.setText ( exercise.getMechanic ( ) );
            equipmentTextView.setText ( exercise.getEquipment ( ) );
            primaryMusclesTextView.setText ( formatListToString ( exercise.getPrimaryMuscles ( ) ) );
            secondaryMusclesTextView.setText ( formatListToString ( exercise.getSecondaryMuscles ( ) ) );

            // Load and set the first exercise image here
            AssetManager assetManager = context.getAssets();
            try {
                String imagePath = "exercises_img/" + exercise.getImageSubdirectory() + "/" + exercise.getImageFilename() + ".jpg";
                InputStream inputStream = assetManager.open(imagePath);
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                exerciseImageView.setImageDrawable(drawable);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    }


}

