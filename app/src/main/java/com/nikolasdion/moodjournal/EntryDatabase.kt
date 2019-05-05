package com.nikolasdion.moodjournal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entry::class], version = 1)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    companion object {
        @Volatile
        private var instance : EntryDatabase? = null

        fun getEntryDatabase(context : Context) : EntryDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, EntryDatabase::class.java, "entry-db").build()
            }
        }
    }
}