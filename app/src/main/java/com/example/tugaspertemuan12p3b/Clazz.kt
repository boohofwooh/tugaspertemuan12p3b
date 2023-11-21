package com.example.tugaspertemuan12p3b

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_table")
data class Clazz(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "major")
    val major: String,

    @ColumnInfo(name = "gpa")
    val gpa: String,

    @ColumnInfo(name = "class_of")
    val class_of: String
)
