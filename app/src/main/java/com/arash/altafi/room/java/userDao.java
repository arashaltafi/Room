package com.arash.altafi.room.java;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface userDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(userModel model);

    @Query("SELECT * FROM tbl_user")
    List<userModel> getAll();

    @Delete
    void delete(userModel model);

    @Update
    void update(userModel model);

}