package com.king.easynote.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.king.easynote.domain.model.Note

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "note.db"
    }
}