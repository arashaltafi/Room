package com.arash.altafi.room.kotlin

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userModel: UserModel)

    @Query("SELECT * FROM tbl_user_kotlin")
    fun getAll(): List<UserModel>

    @Delete
    fun delete(userModel: UserModel)

    @Update
    fun update(userModel: UserModel)

}