package com.example.appclima.repository.database

import com.example.appclima.NoteApplication
import com.example.appclima.repository.Repository
import com.example.appclima.repository.database.model.Note
import kotlinx.coroutines.flow.Flow

class NoteDataBaseRepository : Repository {
    override fun getNote(): Flow<List<Note>> {
        return NoteApplication.application.db.noteDao().getNote()
    }

    override fun getFavoriteNote(): Flow<List<Note>> {
        return NoteApplication.application.db.noteDao().getNoteByFavorite()
    }

    override suspend fun inserNote(note: Note) {
        NoteApplication.application.db.noteDao().insert(note)
    }

    override suspend fun update(note: Note) {
        NoteApplication.application.db.noteDao().upsate(note)
    }
}