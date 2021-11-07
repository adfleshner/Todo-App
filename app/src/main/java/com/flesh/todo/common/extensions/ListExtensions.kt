package com.flesh.todo.common

import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Any.checkIfListOfType(action:(List<T>)->Unit){
    if(this is List<*>) {
        if (all { it is T }) {
            action.invoke(this as List<T>)
            return
        }
        throw IllegalArgumentException("check did not pass all items were not ${T::class.java.simpleName}")
    }
}