package com.king.easynote.base.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel 基类
 *
 * @param ViewState 状态
 * @param ViewEvent 事件
 * @param initState 初始状态
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
abstract class BaseViewModel<ViewState, ViewEvent>(initState: ViewState) : ViewModel() {

    protected val _state = mutableStateOf(initState)
    val state: State<ViewState> = _state

    abstract fun onEvent(event: ViewEvent)

}
