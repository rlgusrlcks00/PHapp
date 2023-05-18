package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DietDAO {
    @Insert
    void setInsertDiet(Diet diet);

    @Update
    void setUpdateDiet(Diet diet);

    @Delete
    void setDeleteDiet(Diet diet);

    @Query("SELECT * FROM Diet")
    List<Diet> getDietAll();
}
