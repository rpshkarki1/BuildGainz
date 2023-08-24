package com.example.buildgainz.DashBoard.ExercisePlan;

import android.annotation.SuppressLint;
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

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    private final List < Exercise > exercises;

    public ExerciseAdapter (Context context , List < Exercise > exercises , RecyclerViewInterface recyclerViewInterface ) {
        this.context = context;
        this.exercises = exercises;
        this.recyclerViewInterface = recyclerViewInterface;

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

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {


        private final TextView exerciseNameTextView;
        private final TextView forceTextView;
        private final TextView levelTextView;
        private final TextView equipmentTextView;
        private final TextView primaryMusclesTextView;
        private final ImageView exerciseImageView;


        public ExerciseViewHolder ( @NonNull View itemView ,RecyclerViewInterface recyclerViewInterface) {
            super ( itemView );
            exerciseImageView = itemView.findViewById(R.id.exerciseImageView);
            exerciseNameTextView = itemView.findViewById ( R.id.name );
            forceTextView = itemView.findViewById ( R.id.force );
            levelTextView = itemView.findViewById ( R.id.level );
            equipmentTextView = itemView.findViewById ( R.id.equipment );
            primaryMusclesTextView = itemView.findViewById ( R.id.primeMuscle );

            itemView.setOnClickListener ( v -> {
                if(recyclerViewInterface != null){
                    int pos = getAdapterPosition ();

                    if(pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick ( pos );
                    }
                }
            } );
        }


        @SuppressLint ( "SetTextI18n" )
        public void bind ( Exercise exercise ) {
            exerciseNameTextView.setText ( exercise.getName ( ) );
            forceTextView.setText ( "FORCE: ".concat (  exercise.getForce ( ).toUpperCase() ) );
            levelTextView.setText ( "LEVEL: ".concat (  exercise.getLevel ( ).toUpperCase() ));
            equipmentTextView.setText ( "EQUIPMENT: ".concat ( exercise.getEquipment ( ).toUpperCase()) );
            primaryMusclesTextView.setText ("PRIMARY MUSCLE: ".concat (  formatListToString  (  exercise.getPrimaryMuscles ( ) ).toUpperCase() ));

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

