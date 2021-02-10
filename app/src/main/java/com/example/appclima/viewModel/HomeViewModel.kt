package com.example.appclima.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.appclima.repository.NoteFactory
import com.example.appclima.repository.database.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel() : ViewModel() {
    private var _allNotas = MutableLiveData<List<Note>>()
    val allNota : LiveData<List<Note>> get() = _allNotas
    private val repository by lazy { NoteFactory() }

    fun getNote(){
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO){
                suspend { repository.getValidator(1)?.getNote()}.invoke()
            }
            if ( result != null){
                Log.d("Clima", "${result?.size}")
                _allNotas.postValue(result)
            }
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                suspend { repository.getValidator(1)?.inserNote(note)}.invoke()
            }
        }
    }
}

class HomeViewModelFactory(private val context: Context) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}