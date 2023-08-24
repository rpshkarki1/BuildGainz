package com.example.buildgainz.DashBoard.ExercisePlan.ViewExercise;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ExerciseCnstrForViewActivity implements Parcelable {
    private String name;
    private String force;
    private String level;
    private List<String> primaryMuscles;
    private List<String> instructions;
    private String imageFilename;
    private String imageSubdirectory;

    public ExerciseCnstrForViewActivity () {
        // Default constructor
    }

    public ExerciseCnstrForViewActivity ( String name, String force, String level, List<String> primaryMuscles,
                                          List<String> instructions, String imageFilename, String imageSubdirectory) {
        this.name = name;
        this.force = force;
        this.level = level;
        this.primaryMuscles = primaryMuscles;
        this.instructions = instructions;
        this.imageFilename = imageFilename;
        this.imageSubdirectory = imageSubdirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getPrimaryMuscles() {
        return primaryMuscles;
    }

    public void setPrimaryMuscles(List<String> primaryMuscles) {
        this.primaryMuscles = primaryMuscles;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getImageSubdirectory() {
        return imageSubdirectory;
    }

    public void setImageSubdirectory(String imageSubdirectory) {
        this.imageSubdirectory = imageSubdirectory;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(force);
        dest.writeString(level);
        dest.writeStringList(primaryMuscles);
        dest.writeStringList(instructions);
        dest.writeString(imageFilename);
        dest.writeString(imageSubdirectory);
    }

    public static final Parcelable.Creator< ExerciseCnstrForViewActivity > CREATOR = new Parcelable.Creator< ExerciseCnstrForViewActivity >() {
        @Override
        public ExerciseCnstrForViewActivity createFromParcel( Parcel in) {
            return new ExerciseCnstrForViewActivity (in);
        }

        @Override
        public ExerciseCnstrForViewActivity[] newArray( int size) {
            return new ExerciseCnstrForViewActivity[size];
        }
    };

    protected ExerciseCnstrForViewActivity ( Parcel in) {
        name = in.readString();
        force = in.readString();
        level = in.readString();
        primaryMuscles = in.createStringArrayList();
        instructions = in.createStringArrayList();
        imageFilename = in.readString();
        imageSubdirectory = in.readString();
    }


}
