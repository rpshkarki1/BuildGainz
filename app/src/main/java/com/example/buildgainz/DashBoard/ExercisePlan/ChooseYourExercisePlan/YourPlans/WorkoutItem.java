package com.example.buildgainz.DashBoard.ExercisePlan.ChooseYourExercisePlan.YourPlans;
public class WorkoutItem {
    private final String  imagePath;
    private final String name;
    private final String repsSets;

    public WorkoutItem(String imagePath, String name, String repsSets) {
        this.imagePath = imagePath;
        this.name = name;
        this.repsSets = repsSets;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public String getRepsSets() {
        return repsSets;
    }
}
