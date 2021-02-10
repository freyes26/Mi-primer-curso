package com.example.appclima.repository

import com.example.appclima.repository.database.model.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getNote(): Flow<List<Note>>
    fun getFavoriteNote(): Flow<List<Note>>
    suspend fun inserNote(note: Note)
    suspend fun update(note: Note)
}