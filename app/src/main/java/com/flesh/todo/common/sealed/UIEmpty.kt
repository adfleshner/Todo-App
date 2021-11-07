package com.flesh.todo.common.sealed

sealed class UIEmpty {
    class StringEmpty(val empty : String) : UIEmpty()
}