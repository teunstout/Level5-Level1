package com.example.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notepad.database.NoteRepository
import com.example.notepad.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditNoteViewModel(application: Application): AndroidViewModel(application){

    private val noteRepository = NoteRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main) // Need to use mainscope, because value from livedata can't be set in the background

    val note = MutableLiveData<Note?>() // Mutable so the data can be changed
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    /**
     * Update the note
     */
    fun updateNote() {
        if (isNoteValid()) { // Use check for data
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNote(note.value!!)
                }
                success.value = true
            }
        }
    }

    /**
     * Check if the data is valid
     */
    private fun isNoteValid(): Boolean {
        return when {
            note.value == null -> { // Check if the value is null.
                error.value = "Note must not be null"
                false
            }
            note.value!!.noteTitle.isBlank() -> { // This case we need to use !!, because we need to check for an empty field.
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }

}