package com.king.easynote.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.king.easynote.base.presentation.viewmodel.BaseViewModel
import com.king.easynote.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@HiltViewModel
class DeleteNoteViewModel @Inject constructor(private val noteUseCases: NoteUseCases) :
    BaseViewModel<DeleteNoteViewModel.DeleteNoteState, DeleteNoteViewModel.DeleteNoteEvent>(
        DeleteNoteState()
    ) {


    override fun onEvent(event: DeleteNoteEvent) {
        when(event){
            is DeleteNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    // 删除笔记
                    noteUseCases.deleteNote.execute(event.noteId)
                }
            }
        }
    }

    /**
     * 状态
     */
    data class DeleteNoteState(val noteId: Int? = null)

    /**
     * 事件
     */
    sealed class DeleteNoteEvent {
        data class DeleteNote(val noteId: Int) : DeleteNoteEvent()
    }
}