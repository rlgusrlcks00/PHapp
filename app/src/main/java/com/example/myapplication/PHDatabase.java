package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;

@Database(entities = {User.class, Calendar.class, Post.class, Comment.class}, version = 8)
public abstract class PHDatabase extends RoomDatabase {

    private static PHDatabase instance;

    public abstract UserDAO UserDao();
    public abstract CalendarDAO CalenderDao();
    public abstract PostDAO PostDao();
    public abstract CommentDAO CommentDao();


    public static synchronized PHDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PHDatabase.class, "ph_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
