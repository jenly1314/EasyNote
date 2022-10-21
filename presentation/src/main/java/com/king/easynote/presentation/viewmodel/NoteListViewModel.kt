package com.king.easynote.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.king.easynote.base.presentation.viewmodel.BaseViewModel
import com.king.easynote.domain.model.Note
import com.king.easynote.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@HiltViewModel
class NoteListViewModel @Inject constructor(private val noteUseCases: NoteUseCases) :
    BaseViewModel<NoteListViewModel.NoteListState, NoteListViewModel.NoteListEvent>(
        NoteListState()
    ) {

    /**
     * 原始数据
     */
    private val originData = mutableStateOf<List<Note>>(ArrayList())

    init {
        viewModelScope.launch {
            noteUseCases.getNoteList.execute(Unit).onEach {
                originData.value = it
                updateNoteList(state.value.text)
            }.launchIn(viewModelScope)
        }
    }

    override fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.SearchNote -> {
                updateNoteList(event.text)
            }
        }
    }

    /**
     * 更新笔记列表
     */
    private fun updateNoteList(text: String) {
        _state.value = state.value.copy(
            text = text,
            notes = if (text.isNotBlank()) originData.value.filter { note ->
                note.title.contains(text) || note.content.contains(text)
            } else originData.value
        )
    }

    /**
     * 状态
     */
    data class NoteListState(
        val text: String = "",
        val notes: List<Note> = emptyList(),
    )

    /**
     * 事件
     */
    sealed class NoteListEvent {
        data class SearchNote(val text: String) : NoteListEvent()
    }
}

