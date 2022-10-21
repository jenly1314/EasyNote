package com.king.easynote.domain.usecase

import com.king.easynote.base.domain.usecase.UseCase
import com.king.easynote.domain.exception.NoteException
import com.king.easynote.domain.model.Note
import com.king.easynote.domain.repository.NoteRepository

/**
 * 保存笔记
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class SaveNoteUseCase(private val repository: NoteRepository) : UseCase<Note, Unit> {

    @Throws(NoteException::class)
    override suspend fun execute(note: Note) {
        if (note.title.isBlank()) {
            throw NoteException("标题不能为空！")
        }
        if (note.content.isBlank()) {
            throw NoteException("内容不能为空！")
        }
        repository.saveNote(note)
    }

}