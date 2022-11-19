package com.king.easynote.base.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel 基类
 *
 * Composable 页面持有 ViewModel 对外暴露的 [state]，并通过调用 [onEvent] 来触发事件，然后由 ViewModel 处理
 * 对应的事件并改变 [internalState] 的值，因为 [internalState] 同 [state]；所以当 [state] 发生变化时，会触发
 * Composable 自动重组，从而更新页面显示
 *
 * @param ViewState 状态
 * @param ViewEvent 事件
 * @param initState 初始状态
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
abstract class BaseViewModel<ViewState, ViewEvent>(initState: ViewState) : ViewModel() {
    /**
     * 状态 - 内部持有可变状态
     */
    protected val internalState = mutableStateOf(initState)

    /**
     * 状态 - 对外暴露只读状态
     */
    val state: State<ViewState> = internalState

    /**
     * 事件 - UI统一通过此函数来触发事件，然后由子 ViewModel 实现并处理对应的事件
     */
    abstract fun onEvent(event: ViewEvent)

}
