package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "User_id",
        childColumns = "user_ID",
        onDelete = CASCADE))
public class myCalendar {
    @PrimaryKey(autoGenerate = true)
    public int data_ID=0;

    public String date;
    public String exercise_type;
    public int exercise_time;
    public int user_ID;

    public int getData_ID() {
        return data_ID;
    }

    public void setData_ID(int data_ID) {
        this.data_ID = data_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExercise_type() {
        return exercise_type;
    }

    public void setExercise_type(String exercise_type) {
        this.exercise_type = exercise_type;
    }

    public int getExercise_time() {
        return exercise_time;
    }

    public void setExercise_time(int exercise_time) {
        this.exercise_time = exercise_time;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
}
