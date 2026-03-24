package com.tru.core.bases.base_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tru.core.error.AppError
import com.tru.core.error.AppErrorHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class BaseViewModel<Intent : ViewIntent, State : ViewState, Event : UiEvent>(

    initialState: State

) : ViewModel(), AppErrorHandler {

    protected val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    protected val _uiEvent = MutableSharedFlow<Event>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val intents = MutableSharedFlow<Intent>(extraBufferCapacity = 1)


    init {
        observeIntents()
    }


    private fun observeIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                handleIntent(intent)
            }
        }
    }


    protected abstract suspend fun handleIntent(intent: Intent)

    fun sendIntent(intent: Intent) {
        intents.tryEmit(intent)
    }


    protected fun updateState(reducer: State.() -> State) {
        _uiState.update(reducer)
    }


    override fun handleError(error: AppError, callback: AppError.() -> Unit) {
        error.logError()
        callback(error)
    }

}

interface ViewState

interface ViewIntent

interface UiEvent