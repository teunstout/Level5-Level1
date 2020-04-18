package com.example.notepad.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notepad.model.DateConverter
import com.example.notepad.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@TypeConverters(DateConverter::class)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteInterface // Use the interface to make the note app

    // In companion object to create a singleton
    companion object {
        private const val DATABASE_NAME = "NOTEPAD_DATABASE" // database name

        @Volatile // Every action is immediately visible to all.
        private var INSTANCE: NoteDatabase? = null // Make instance of this database

        fun getDatabase(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class.java) {
                    // make database synchronized so that data is not edited at the same time. Data won't go faulty now.
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder( // if there is no instance make a instance
                            context.applicationContext,
                            NoteDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object :
                                RoomDatabase.Callback() { // Empty note needs to be created with a new database
                                override fun onCreate(db: SupportSQLiteDatabase) { // overwrite the oncreate
                                    super.onCreate(db)
                                    INSTANCE?.let { database -> // If instance is null make a Date
                                        CoroutineScope(Dispatchers.IO).launch { // Launch Corountine
                                            database.noteDao() // Call the database it's interface
                                                .insertNote(Note("Title", Date(), "")) // Insert empty note
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }


}