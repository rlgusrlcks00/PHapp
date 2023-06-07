package com.example.myapplication;

public class RecommendationResponse {
    private String exerciseName;
    private String description;
    private String imageUrl;

    public RecommendationResponse(String exerciseName, String description, String imageUrl) {
        this.exerciseName = exerciseName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
