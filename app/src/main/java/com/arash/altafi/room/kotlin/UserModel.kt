package com.arash.altafi.room.kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user_kotlin")
class UserModel {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "user_name")
    var name: String? = null

    @ColumnInfo(name = "user_family")
    var family: String? = null

    @ColumnInfo(name = "user_age")
    var age: Int? = null
}