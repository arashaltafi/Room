package com.arash.altafi.room.java;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_user")
public class user_model {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "user_name")
    String name;
    @ColumnInfo(name = "user_family")
    String family;
    @ColumnInfo(name = "user_age")
    int age;

}
