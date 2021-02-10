package com.example.appclima.repository.database.dao

import androidx.room.*
import com.example.appclima.repository.database.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getNote(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE favorite = 1")
    fun getNoteByFavorite(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("DELETE FROM Note")
    fun deleteAll()

    @Update
    fun upsate(note: Note)


}