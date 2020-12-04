package com.icm.githubusers.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.icm.githubusers.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract UserDAO userDAO();
}