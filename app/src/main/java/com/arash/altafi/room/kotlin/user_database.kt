package com.arash.altafi.room.kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1 , entities = arrayOf(user_model::class))
abstract class user_database:RoomDatabase() {

    companion object
    {
        var userDatabase:user_database ?= null
        fun getAppDataBase(context: Context) : user_database?
        {
            if (userDatabase == null)
            {
                synchronized(user_database::class)
                {
                    userDatabase = Room.databaseBuilder(context , user_database::class.java , "db_user_kotlin").allowMainThreadQueries().build()
                }
            }
            return userDatabase
        }
    }

    abstract fun UserDao() : user_dao

}