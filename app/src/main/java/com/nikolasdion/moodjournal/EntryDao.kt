package com.nikolasdion.moodjournal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntryDao {

    @Query("SELECT * from entry_table order by date desc")
    fun getAllEntries() : LiveData<List<Entry>>

    @Query("SELECT * from entry_table WHERE id = :entryId")
    fun getEntry(entryId: Int) : LiveData<Entry>

    @Insert
    suspend fun insert(event: Entry)

    @Update
    suspend fun update(event: Entry)

    @Query("DELETE FROM entry_table")
    fun deleteAll()
}