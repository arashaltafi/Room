package com.arash.altafi.room.kotlin

import androidx.room.*

@Dao
interface user_dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModel: user_model)

    @Query("SELECT * FROM tbl_user_kotlin")
    fun getAll() : List<user_model>

    @Delete
    fun delete(userModel: user_model)

    @Update
    fun update(userModel: user_model)

}