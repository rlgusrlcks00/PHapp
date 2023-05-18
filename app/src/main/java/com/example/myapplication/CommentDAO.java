package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CommentDAO {
    @Insert
    void setInsertComment(Comment comment);

    @Update
    void setUpdateComment(Comment comment);

    @Delete
    void setDeleteComment(Comment comment);

    @Query("SELECT * FROM Comment")
    List<Comment> getCommentAll();
}
