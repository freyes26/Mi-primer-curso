package com.example.appclima.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.appclima.repository.NoteFactory
import com.example.appclima.repository.database.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel() : ViewModel() {
    private val repository by lazy { NoteFactory() }
    val allNote = repository.getValidator(1).getNote().asLiveData()

    fun updateNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                suspend { repository.getValidator(1).update(note) }.invoke()
            }
        }
    }
}