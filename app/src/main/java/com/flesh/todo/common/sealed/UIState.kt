package com.flesh.todo.common.sealed

sealed class UIState {

    object NotStarted : UIState()
    object Loading : UIState()
    data class Success<T>(val result: T): UIState()
    data class Empty<Empty: UIEmpty>(val empty: Empty) : UIState()
    data class Error<Error: UIError>(val error: Error): UIState()

}