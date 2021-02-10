package com.example.appclima.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appclima.repository.database.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getNote(): List<Note>

    @Query("SELECT * FROM Note WHERE favorite = 1")
    fun getNoteByFavorite(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Query("DELETE FROM Note")
    fun deleteAll()

}