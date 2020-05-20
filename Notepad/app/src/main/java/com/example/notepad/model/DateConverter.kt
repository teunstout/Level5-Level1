package com.example.notepad.model

import androidx.room.TypeConverter
import java.util.*

/**
 * When you use type converters you can use the Date now in your entity and in you interface
 * To write query's and to save data.
 */
class DateConverter {
    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun longToDate(longValue: Long?): Date? {
        return longValue?.let { Date(it) } //   // value == null ? null : new Date(value);
    }
}