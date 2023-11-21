package com.example.tugaspertemuan12p3b

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Clazz::class], version = 2, exportSchema = false)
abstract class ClazzRoomDatabase : RoomDatabase() {
    abstract fun clazzDao(): ClazzDao

    companion object {
        @Volatile
        private var INSTANCE: ClazzRoomDatabase? = null

        fun getInstance(context: Context): ClazzRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClazzRoomDatabase::class.java, "clazz_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
