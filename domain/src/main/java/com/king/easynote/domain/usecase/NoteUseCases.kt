package com.king.easynote.domain.usecase

/**
 * 笔记 - 增删查改
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
data class NoteUseCases(
    val getNoteList: GetNoteListUseCase,
    val getNote: GetNoteUseCase,
    val saveNote: SaveNoteUseCase,
    val deleteNote: DeleteNoteUseCase
)