package com.example.appclima.repository.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
class Note(
        @PrimaryKey(autoGenerate = true) val id: Int?,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "comment") val comment: String,
        @ColumnInfo(name = "favorite") val favorite: Boolean,
        @ColumnInfo(name = "color") val color: String
)

