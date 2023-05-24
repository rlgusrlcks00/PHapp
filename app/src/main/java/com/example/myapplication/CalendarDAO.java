package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CalendarDAO {
    @Insert
    void setInsertCalendar(myCalendar calendar);

    @Update
    void setUpdateCalendar(myCalendar calendar);

    @Delete
    void setDeleteCalendar(myCalendar calendar);

    @Query("SELECT * FROM myCalendar")
    List<myCalendar> getCalendarAll();
    @Query("SELECT * FROM myCalendar WHERE date = :selectedDate AND user_id = :userId")
    List<myCalendar> getExerciseByDateAndUserId(String selectedDate, int userId);

    @Query("SELECT * FROM myCalendar WHERE data_ID = :exerciseId")
    myCalendar getExerciseById(int exerciseId);

}
