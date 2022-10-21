package com.king.easynote.domain.repository

import com.king.easynote.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Repository：定义数据仓库接口，然后在 data 层进行实现
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
interface NoteRepository {

    /**
     * 查询笔记列表
     */
    suspend fun queryNoteList(): Flow<List<Note>>

    /**
     * 根据ID查询对应的笔记
     */
    suspend fun queryNoteById(noteId: Int): Note?

    /**
     * 保存笔记
     */
    suspend fun saveNote(note: Note)


    /**
     * 根据ID删除对应的笔记
     */
    suspend fun deleteNote(noteId: Int)
}