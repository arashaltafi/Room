package com.arash.altafi.room.java;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface user_dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(user_model model);

    @Query("SELECT * FROM tbl_user")
    List<user_model> getAll();

    @Delete
    void delete(user_model model);

    @Update
    void update(user_model model);

}