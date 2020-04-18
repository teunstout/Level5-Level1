package com.example.notepad.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notepad.model.Note

@Dao
interface NoteInterface {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM Note LIMIT 1")
    fun getNote(): LiveData<Note?>

    @Update
    suspend fun updateNote(note: Note)
}