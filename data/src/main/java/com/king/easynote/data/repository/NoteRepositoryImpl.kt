package com.king.easynote.data.repository

import com.king.easynote.data.datasource.NoteDao
import com.king.easynote.domain.model.Note
import com.king.easynote.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * NoteRepository 实现
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun queryNoteList(): Flow<List<Note>> {
        return withContext(Dispatchers.IO) { noteDao.queryNoteList() }
    }

    override suspend fun queryNoteById(noteId: Int): Note? {
        return withContext(Dispatchers.IO) { noteDao.queryNoteById(noteId) }
    }

    override suspend fun saveNote(note: Note) {
        return withContext(Dispatchers.IO) { noteDao.insertOrReplaceNote(note) }
    }

    override suspend fun deleteNote(noteId: Int) {
        return withContext(Dispatchers.IO) { noteDao.deleteNote(noteId) }
    }
}