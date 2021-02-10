package com.example.appclima.repository

import androidx.lifecycle.LiveData
import com.example.appclima.repository.database.model.Note

interface Repository {
    suspend fun getNote(): List<Note>
    suspend fun getFavoriteNote(): LiveData<List<Note>>
    suspend fun inserNote(note:Note)
}