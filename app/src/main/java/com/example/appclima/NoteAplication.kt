package com.example.appclima

import android.app.Application
import com.example.appclima.repository.database.NoteDataBase
import com.facebook.stetho.Stetho

class NoteApplication : Application() {

    companion object {
        lateinit var application: NoteApplication
    }

    val db by lazy { NoteDataBase.getDatabase(applicationContext) }
    override fun onCreate() {
        super.onCreate()
        application = this
        Stetho.initializeWithDefaults(this);
    }

}