package com.example.appclima.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appclima.repository.NoteFactory
import com.example.appclima.repository.database.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewNoteViewModel : ViewModel() {

    private val repository by lazy { NoteFactory() }
    private var _title = MutableLiveData("")
    val title get() = _title
    private var _description = MutableLiveData("")
    val description get() = _description
    private var _favorite = MutableLiveData(false)
    val favorite get() = _favorite
    private var _color = MutableLiveData("")
    val color get() = _color


    fun saveNote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                suspend {
                    repository.getValidator(1).inserNote(Note(null, title.value!!, description.value!!, favorite.value!!, color.value!!))
                }.invoke()
            }
        }
    }

    fun setColor(colorSet: String) {
        _color.value = colorSet
    }

    fun setFavorite(favoriteSet: Boolean) {
        _favorite.value = favoriteSet
    }
}