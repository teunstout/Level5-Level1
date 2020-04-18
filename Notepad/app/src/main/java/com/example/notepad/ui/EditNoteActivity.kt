package com.example.notepad.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notepad.R
import com.example.notepad.viewmodel.EditNoteViewModel
import kotlinx.android.synthetic.main.activity_notepad.*
import kotlinx.android.synthetic.main.content_edit_notepad.*
import java.util.*

class EditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

    private lateinit var editNoteViewModel: EditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notepad)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            // Edit the notepad data when save
            editNoteViewModel.note.value?.apply {
                noteTitle = etTitle.text.toString()
                lastUpdated = Date()
                noteText = etNotes.text.toString()
            }

            editNoteViewModel.updateNote()
        }

    }

    private fun initViewModel() {
        editNoteViewModel = ViewModelProvider(this).get(EditNoteViewModel::class.java)
        editNoteViewModel.note.value = intent.extras?.getParcelable(EXTRA_NOTE)!!

        editNoteViewModel.note.observe(this, androidx.lifecycle.Observer { note ->
            if (note != null) {
                etTitle.setText(note.noteTitle)
                etNotes.setText(note.noteText)
            }
        })

        // If error display error in toast
        editNoteViewModel.error.observe(this, androidx.lifecycle.Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        // When success finish intent
        editNoteViewModel.success.observe(this, androidx.lifecycle.Observer { success ->
            if (success) finish()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {  // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
