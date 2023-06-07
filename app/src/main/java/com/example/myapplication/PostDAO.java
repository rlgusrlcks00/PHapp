package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PostDAO {
    @Insert
    void setInsertPost(Post post);

    @Update
    void setUpdatePost(Post post);

    @Delete
    void setDeletePost(Post post);

    @Query("SELECT * FROM Post")
    List<Post> getPostAll();

    @Query("SELECT COUNT(*) FROM Post")
    int getTotalPosts();

    @Query("SELECT title FROM post LIMIT :pageSize OFFSET :offset")
    List<String> getPageTitles(int pageSize, int offset);

    @Query("SELECT * FROM post WHERE post_ID = :postId")
    Post getPostById(int postId);

}
