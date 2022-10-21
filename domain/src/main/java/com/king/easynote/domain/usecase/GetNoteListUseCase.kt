package com.king.easynote.domain.usecase

import com.king.easynote.base.domain.usecase.UseCase
import com.king.easynote.domain.model.Note
import com.king.easynote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * 获取笔记列表
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class GetNoteListUseCase(private val repository: NoteRepository) : UseCase<Unit, Flow<List<Note>>> {

    override suspend fun execute(params: Unit): Flow<List<Note>> {
        return repository.queryNoteList()
    }
}