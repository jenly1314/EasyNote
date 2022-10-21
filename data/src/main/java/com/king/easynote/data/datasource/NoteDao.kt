package com.king.easynote.data.datasource

import androidx.room.*
import com.king.easynote.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Dao
interface NoteDao {

    /**
     * 查询笔记列表
     */
    @Query("SELECT * FROM note ORDER BY timestamp DESC")
    fun queryNoteList(): Flow<List<Note>>

    /**
     * 根据ID查询对应的笔记
     */
    @Query("SELECT * FROM note WHERE id = :noteId")
    suspend fun queryNoteById(noteId: Int): Note?

    /**
     * 插入笔记；无则插入，有则替换
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceNote(note: Note)

    /**
     * 根据ID删除对应的笔记
     */
    @Query("DELETE FROM note WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)
}