package com.nikolasdion.moodjournal

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class EntryRepository private constructor(private val entryDao: EntryDao) {

    val allEntries: LiveData<List<Entry>> = entryDao.getAllEntries()

    @WorkerThread
    suspend fun insert(entry : Entry) {
        entryDao.insert(entry)
    }

    companion object {
        @Volatile
        private var instance: EntryRepository? = null

        fun getInstance(entryDao: EntryDao): EntryRepository =
            instance ?: synchronized(this) {
                instance ?: EntryRepository(entryDao).also { instance = it }
            }
    }
}