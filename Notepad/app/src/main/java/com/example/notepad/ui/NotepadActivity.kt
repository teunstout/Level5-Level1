package com.example.notepad.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.notepad.R
import com.example.notepad.viewmodel.NoteViewholder

import kotlinx.android.synthetic.main.activity_notepad.*
import kotlinx.android.synthetic.main.content_edit_notepad.*
import kotlinx.android.synthetic.main.content_notepad.*

class NotepadActivity : AppCompatActivity() {

    //    private lateinit var noteViewholder: NoteViewholder
    private val noteViewholder: NoteViewholder by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notepad)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

    }

    private fun initViews() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun initViewModel() {
//        noteViewholder = ViewModelProvider(this).get(NoteViewholder::class.java) // Instantiate the note viewholder


        noteViewholder.note.observe(this, Observer { note ->
            if (note != null) {
                tvTitle.text = note.noteTitle
                tvLastUpdated.text = getString(R.string.last_updated, note.lastUpdated.toString())
                tvNote.text = note.noteText
            }
        })
    }

}
