package com.example.appclima.repository.database

import androidx.lifecycle.LiveData
import com.example.appclima.NoteApplication
import com.example.appclima.repository.Repository
import com.example.appclima.repository.database.model.Note

class NoteDataBaseRepository : Repository {
    override suspend fun getNote(): List<Note> {
        return NoteApplication.application.db.noteDao().getNote()
    }

    override suspend fun getFavoriteNote(): LiveData<List<Note>> {
        return NoteApplication.application.db.noteDao().getNoteByFavorite()
    }

    override suspend fun inserNote(note: Note) {
        NoteApplication.application.db.noteDao().insert(note)
    }
}