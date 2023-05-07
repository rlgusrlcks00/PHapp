package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 4)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO UserDao();
}
