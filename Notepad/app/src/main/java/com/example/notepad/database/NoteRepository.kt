package com.example.notepad.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notepad.model.Note

class NoteRepository(context: Context) {

    private val noteDao: NoteInterface

    init {
        val database = NoteDatabase.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotepad(): LiveData<Note?> = noteDao.getNote()

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
}