package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.annotation.NonNull;

@Entity(tableName = "Post",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "User_id",
                childColumns = "user_ID",
                onDelete = ForeignKey.CASCADE))
public class Post {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "post_ID")
    private int postID;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "user_ID")
    private int userID;

    public Post(int postID, String title, String content, String date, int userID) {
        this.postID = postID;
        this.title = title;
        this.content = content;
        this.date = date;
        this.userID = userID;
    }


    // getters and setters
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
