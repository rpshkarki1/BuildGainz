package com.example.buildgainz.DashBoard.ExercisePlan;


import java.util.List;

public class Exercise {
    private String name;
    private String force;
    private String level;
    private String mechanic;

    private String equipment;
    private List < String > primaryMuscles;
    private List < String > secondaryMuscles;

    private List < String > instructions;
    private String category;

    private String imageFilename;
    private String imageSubdirectory;
    private String id;

    // Constructors
    public Exercise ( ) {
    }

    public Exercise ( String name , String force , String level , String mechanic , String equipment ,
                      List < String > primaryMuscles , List < String > secondaryMuscles ,
                      List < String > instructions , String category ,
                      String imageFilename , String imageSubdirectory , String id ) {
        this.name = name;
        this.force = force;
        this.level = level;
        this.equipment = equipment;
        this.primaryMuscles = primaryMuscles;
        this.instructions = instructions;
        this.category = category;
        this.imageFilename = imageFilename;
        this.imageSubdirectory = imageSubdirectory;
        this.mechanic = mechanic;
        this.secondaryMuscles= secondaryMuscles;
        this.id = id;
    }

    // Getters and Setters
    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
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

    public String getMechanic ( ) {
        return mechanic;
    }

    public void setMechanic ( String mechanic ) {
        this.mechanic = mechanic;
    }

    public String getEquipment ( ) {
        return equipment;
    }

    public void setEquipment ( String equipment ) {
        this.equipment = equipment;
    }

    public List < String > getPrimaryMuscles ( ) {
        return primaryMuscles;
    }

    public void setPrimaryMuscles ( List < String > primaryMuscles ) {
        this.primaryMuscles = primaryMuscles;
    }

    public List < String > getSecondaryMuscles ( ) {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles ( List < String > secondaryMuscles ) {
        this.secondaryMuscles = secondaryMuscles;
    }


    public List < String > getInstructions ( ) {
        return instructions;
    }

    public void setInstructions ( List < String > instructions ) {
        this.instructions = instructions;
    }

    public String getCategory ( ) {
        return category;
    }

    public void setCategory ( String category ) {
        this.category = category;
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

    public String getId ( ) {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }


}
