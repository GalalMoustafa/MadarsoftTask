package com.galalmoustafa.madarsofttask.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.galalmoustafa.madarsofttask.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * From User")
    LiveData<List<User>> LoadAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertUser(User user);
}
