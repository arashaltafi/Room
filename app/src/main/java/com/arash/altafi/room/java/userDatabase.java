package com.arash.altafi.room.java;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = userModel.class)
public abstract class userDatabase extends RoomDatabase {

    private static userDatabase database;

    public static userDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room
                    .databaseBuilder(context, userDatabase.class, "db_user")
//                        .createFromAsset("database/myapp.db")  // For db first
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }

    public abstract userDao dao();

}