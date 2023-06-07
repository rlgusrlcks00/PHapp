package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.ColumnInfo;
import androidx.annotation.NonNull;

@Entity(tableName = "Comment",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "User_id",
                        childColumns = "user_ID",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Post.class,
                        parentColumns = "post_ID",
                        childColumns = "post_ID",
                        onDelete = ForeignKey.CASCADE)
        })
public class Comment {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "comment_ID")
    private int commentID;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "user_ID")
    private int userID;

    @ColumnInfo(name = "post_ID")
    private int postID;

    // getters and setters
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
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

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}
