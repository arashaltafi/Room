package com.arash.altafi.room.java;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1 , entities = user_model.class)
public abstract class user_database extends RoomDatabase {

    private static user_database database;

    public static user_database getDatabase(Context context) {
        if (database == null)
        {
            database = Room.databaseBuilder(context , user_database.class , "db_user").allowMainThreadQueries().build();
        }
        return database;
    }

    public abstract user_dao dao();

}