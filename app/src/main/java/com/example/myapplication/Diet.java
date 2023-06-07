package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.annotation.NonNull;

@Entity(tableName = "diets",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "User_id",
                        childColumns = "user_ID",
                        onDelete = ForeignKey.CASCADE)
        })
public class Diet {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "diet_ID")
    private int dietID;

    @ColumnInfo(name = "meal_time")
    private String mealTime;

    @ColumnInfo(name = "food_name")
    private String foodName;

    @ColumnInfo(name = "calories")
    private int calories;

    @ColumnInfo(name = "user_ID")
    private int userID;

    // getters and setters
    public int getDietID() {
        return dietID;
    }

    public void setDietID(int dietID) {
        this.dietID = dietID;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
