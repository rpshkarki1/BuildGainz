package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class WorkoutItem implements Parcelable {
    public static final Parcelable.Creator < WorkoutItem > CREATOR = new Parcelable.Creator < WorkoutItem > ( ) {
        @Override
        public WorkoutItem createFromParcel ( Parcel in ) {
            return new WorkoutItem ( in );
        }

        @Override
        public WorkoutItem[] newArray ( int size ) {
            return new WorkoutItem[size];
        }
    };
    private String imagePath;
    private String name;
    private String repsSets;
    private String force;
    private String level;
    private List < String > primaryMuscles;
    private List < String > instructions;
    private String imageFilename;
    private String imageSubdirectory;
    private String imageName;

    public String getImageName ( ) {
        return imageName;
    }

    public void setImageName ( String imageName ) {
        this.imageName = imageName;
    }

    public WorkoutItem ( ) {
        //Default
    }

    public WorkoutItem ( String imagePath ,String imageName, String name , String repsSets ) {
        this.imagePath = imagePath;
        this.name = name;
        this.repsSets = repsSets;
        this.imageName=imageName;
    }

    public WorkoutItem ( String name , String force , String level , List < String > primaryMuscles ,
                         List < String > instructions , String imageFilename , String imageSubdirectory ) {
        this.name = name;
        this.force = force;
        this.level = level;
        this.primaryMuscles = primaryMuscles;
        this.instructions = instructions;
        this.imageFilename = imageFilename;
        this.imageSubdirectory = imageSubdirectory;
    }

    public WorkoutItem ( Parcel in ) {
        name = in.readString ( );
        force = in.readString ( );
        level = in.readString ( );
        primaryMuscles = in.createStringArrayList ( );
        instructions = in.createStringArrayList ( );
        imageFilename = in.readString ( );
        imageSubdirectory = in.readString ( );
    }




    public String getImagePath ( ) {
        return imagePath ;
    }

    public void setImagePath ( String imagePath ) {
        this.imagePath = imagePath;
    }

    public String getName ( ) {
        return name;
    }


    public void setName ( String name ) {
        this.name = name;
    }

    public String getRepsSets ( ) {
        return repsSets;
    }

    public void setRepsSets ( String repsSets ) {
        this.repsSets = repsSets;
    }

    @Override
    public int describeContents ( ) {
        return 0;
    }

    @Override
    public void writeToParcel ( @NonNull Parcel dest , int flags ) {
        dest.writeString ( name );
        dest.writeString ( force );
        dest.writeString ( level );
        dest.writeStringList ( primaryMuscles );
        dest.writeStringList ( instructions );
        dest.writeString ( imageFilename );
        dest.writeString ( imageSubdirectory );

    }

    public String getForce ( ) {
        return force;
    }

    public void setForce ( String force ) {
        this.force = force;
    }

    public String getLevel ( ) {
        return level;
    }

    public void setLevel ( String level ) {
        this.level = level;
    }

    public List < String > getPrimaryMuscles ( ) {
        return primaryMuscles;
    }

    public void setPrimaryMuscles ( List < String > primaryMuscles ) {
        this.primaryMuscles = primaryMuscles;
    }

    public List < String > getInstructions ( ) {
        return instructions;
    }

    public void setInstructions ( List < String > instructions ) {
        this.instructions = instructions;
    }

    public String getImageFilename ( ) {
        return imageFilename;
    }

    public void setImageFilename ( String imageFilename ) {
        this.imageFilename = imageFilename;
    }

    public String getImageSubdirectory ( ) {
        return imageSubdirectory;
    }

    public void setImageSubdirectory ( String imageSubdirectory ) {
        this.imageSubdirectory = imageSubdirectory;
    }
}
