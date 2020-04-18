package com.example.notepad.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Note(
    var noteTitle: String,
    var lastUpdated: Date,
    var noteText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable
