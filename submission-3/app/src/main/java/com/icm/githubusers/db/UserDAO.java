package com.icm.githubusers.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.icm.githubusers.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user ORDER BY uid DESC")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM user ORDER BY uid DESC")
    Cursor selectAll();

    @Insert
    void insertAll(User... users);

    @Query("DELETE FROM user WHERE uid = :uid")
    void deleteByUsername(int uid);
}
