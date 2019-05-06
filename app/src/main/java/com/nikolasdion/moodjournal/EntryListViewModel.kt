package com.nikolasdion.moodjournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EntryListViewModel(application: Application) : AndroidViewModel(application) {

    private var job = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val repository : EntryRepository

    val allEntries : LiveData<List<Entry>>

    init {
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository = EntryRepository.getInstance(entryDao)

        allEntries = repository.allEntries
    }

    fun insert(entry: Entry) {
        uiScope.launch(Dispatchers.IO) {
            repository.insert(entry)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}