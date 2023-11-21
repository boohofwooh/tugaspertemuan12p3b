package com.example.tugaspertemuan12p3b

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClazzDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(clazz: Clazz)

    @Update
    fun update(clazz: Clazz)

    @Delete
    fun delete(clazz: Clazz)

    @get:Query("SELECT * from class_table ORDER BY id ASC")
    val allClasses: LiveData<List<Clazz>>
}