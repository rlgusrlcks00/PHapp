package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoticeDAO {
    @Insert
    void setInsertNotice(Notice notice);

    @Update
    void setUpdateNotice(Notice notice);

    @Delete
    void setDeleteNotice(Notice notice);

    @Query("SELECT * FROM Notice")
    List<Notice> getNoticeAll();
}
