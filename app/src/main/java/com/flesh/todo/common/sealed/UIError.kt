package com.flesh.todo.common.sealed

sealed class UIError {

    class StringError(val error : String) : UIError()

}