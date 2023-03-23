package com.arash.altafi.room.kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [UserModel::class])
abstract class UserDatabase : RoomDatabase() {

    companion object {
        var userDatabase: UserDatabase? = null
        fun getAppDataBase(context: Context): UserDatabase? {
            if (userDatabase == null) {
                synchronized(UserDatabase::class) {
                    userDatabase =
                        Room
                            .databaseBuilder(context, UserDatabase::class.java, "db_user_kotlin")
//                        .createFromAsset("database/myapp.db")  // For db first
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return userDatabase
        }
    }

    abstract fun UserDao(): UserDao

}