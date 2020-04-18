package com.example.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notepad.database.NoteRepository

/**
 * All the business logic of Notepad Activity
 */
class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext) // Get note repository from application
    val note = noteRepository.getNotepad() // Get live data from the repository
}
