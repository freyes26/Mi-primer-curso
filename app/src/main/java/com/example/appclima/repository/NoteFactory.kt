package com.example.appclima.repository

import com.example.appclima.repository.database.NoteDataBaseRepository

class NoteFactory {
    private fun isDataBase(selector : Int) = selector == 1
    //private fun isRetrofit(selector : Int) = s

    fun getValidator(selector : Int):Repository? = when {
        isDataBase(selector) -> NoteDataBaseRepository()
        //isRetrofit(selector) -> MoviDataRepository()
        else -> null
    }
}