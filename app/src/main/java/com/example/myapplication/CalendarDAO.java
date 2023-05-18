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
    void setInsertCalendar(Calendar calendar);

    @Update
    void setUpdateCalendar(Calendar calendar);

    @Delete
    void setDeleteCalendar(Calendar calendar);

    @Query("SELECT * FROM Calendar")
    List<Calendar> getCalendarAll();
}
