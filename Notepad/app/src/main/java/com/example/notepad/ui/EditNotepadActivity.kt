package com.example.notepad.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notepad.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_notepad.*

class EditNotepadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notepad)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}